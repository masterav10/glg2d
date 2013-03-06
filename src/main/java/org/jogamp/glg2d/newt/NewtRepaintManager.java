package org.jogamp.glg2d.newt;

import java.awt.Point;
import java.awt.Window;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.media.opengl.GLAutoDrawable;
import javax.swing.JComponent;
import javax.swing.RepaintManager;
import javax.swing.SwingUtilities;

import org.jogamp.glg2d.GLGraphics2D;

import com.jogamp.newt.opengl.GLWindow;

/**
 * This repaint manager extends the default implementation by intercepting Swing
 * components that are drawn within a {@link GLG2DWindow}.
 * 
 * When it is time to repaint the request, clients will call the
 * {@link #setCurrentDrawable(GLAutoDrawable)} to identify which component is
 * currently drawing.
 * 
 * @author Dan Avila
 * @see NewtRepaintGLEventListener
 */
public class NewtRepaintManager extends RepaintManager
{
	private static final NewtRepaintManager INST = new NewtRepaintManager();

	// A map of direct components.
	private Map<GLAutoDrawable, Set<JComponent>> dirtyComponents = new HashMap<>();
	private Set<JComponent> activeRepaints = new HashSet<>();

	private GLAutoDrawable currentDrawable;

	public NewtRepaintManager()
	{
		setDoubleBufferingEnabled(false);
	}

	public static NewtRepaintManager get()
	{
		return INST;
	}

	/**
	 * This method is thread safe.
	 */
	@Override
	public void addDirtyRegion(JComponent c, int x, int y, int w, int h)
	{
		Window window = SwingUtilities.getWindowAncestor(c);

		if (window instanceof GLG2DFrame)
		{
			GLWindow drawable = ((GLG2DFrame) window).getWindow();

			Set<JComponent> dirtyComponentsForDrawing = dirtyComponents
			        .get(drawable);

			if (dirtyComponentsForDrawing == null)
			{
				dirtyComponentsForDrawing = new HashSet<>();
				dirtyComponents.put(drawable, dirtyComponentsForDrawing);
			}

			synchronized (this)
			{
				dirtyComponentsForDrawing.add(c);
			}
		}
	}

	/**
	 * This method paints all dirty Swing components that belong to the current
	 * {@link GLAutoDrawable GL drawable}.
	 * 
	 * This method is not thread safe, and should only be called from a single
	 * thread.
	 */
	void paintDirtyGLComponents(GLGraphics2D graphics)
	{
		Collection<JComponent> dirtyComponentsForDrawable = dirtyComponents
		        .get(currentDrawable);

		if (dirtyComponentsForDrawable != null)
		{
			synchronized (this)
			{
				// prevents addDirtyRegion(..) calls from repaints from
				// affecting traversal.
				activeRepaints.addAll(dirtyComponentsForDrawable);
				dirtyComponentsForDrawable.clear();
			}

			for (JComponent comp : activeRepaints)
			{
				int x = comp.getX();
				int y = comp.getY();

				Window window = SwingUtilities.getWindowAncestor(comp);

				Point p = SwingUtilities.convertPoint(comp, x, y, window);

				System.err.println(comp.getClass() + " " + p);

				graphics.translate(p.x, p.y);

				if (comp.isShowing() && comp.isValid())
				{
					comp.paint(graphics);
				}
			}

			activeRepaints.clear();
		}
	}

	/**
	 * Sets the current drawable to used by this repaint manager.
	 * 
	 * @param currentDrawable
	 *            - the target current drawable.
	 */
	void setCurrentDrawable(GLAutoDrawable currentDrawable)
	{
		this.currentDrawable = currentDrawable;
	}
}
