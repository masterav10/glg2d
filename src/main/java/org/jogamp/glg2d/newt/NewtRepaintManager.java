package org.jogamp.glg2d.newt;

import java.awt.Rectangle;
import java.awt.Window;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;

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
	private Queue<Rectangle> rectCache = new ConcurrentLinkedDeque<Rectangle>();

	// Maintains dirty components. Using an identity map because the key may
	// change.
	private Map<JComponent, Rectangle> dirtyRegions = new HashMap<>();
	private Collection<JComponent> activeRepaints = new ArrayList<>(20);

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

			Rectangle dirtyRegion = dirtyRegions.get(c);

			synchronized (this)
			{
				if (dirtyRegion == null)
				{
					dirtyRegion = acquireRect();

					dirtyRegion.setBounds(x, y, w, h);

					dirtyComponentsForDrawing.add(c);
					dirtyRegions.put(c, dirtyRegion);
				}
				else
				{
					Rectangle newBounds = acquireRect();
					newBounds.setBounds(x, y, w, h);

					Rectangle.union(dirtyRegion, newBounds, dirtyRegion);

					recycleRect(newBounds);
				}
			}
		}
	}

	private Rectangle acquireRect()
	{
		Rectangle rect = rectCache.poll();

		if (rect == null)
		{
			rect = new Rectangle();
		}

		return rect;
	}

	private void recycleRect(Rectangle rect)
	{
		rectCache.add(rect);
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
				Rectangle dirtyRegion = dirtyRegions.remove(comp);

				// comp.paintImmediately(0, 0, comp.getWidth(),
				// comp.getHeight());
				graphics.translate(comp.getX(), comp.getY());

				if (comp.isShowing() && comp.isValid())
				{
					comp.paint(graphics);
				}

				recycleRect(dirtyRegion);
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
