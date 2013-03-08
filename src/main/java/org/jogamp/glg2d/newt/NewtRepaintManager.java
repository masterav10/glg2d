package org.jogamp.glg2d.newt;

import java.awt.Window;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

import javax.media.opengl.GLAutoDrawable;
import javax.swing.JComponent;
import javax.swing.JToolTip;
import javax.swing.RepaintManager;
import javax.swing.SwingUtilities;

import org.jogamp.glg2d.GLGraphics2D;

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
	private Map<Window, Set<JComponent>> dirtyComponents = new HashMap<>();
	private Map<Window, Boolean> fullRepaints = new ConcurrentHashMap<>();

	private Set<JComponent> activeRepaints = new LinkedHashSet<>();

	public NewtRepaintManager()
	{
		setDoubleBufferingEnabled(false);
	}

	public static NewtRepaintManager get()
	{
		return INST;
	}

	@Override
	public void addDirtyRegion(Window window, int x, int y, int w, int h)
	{
		synchronized (this)
		{
			fullRepaints.put(window, true);
		}
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
			synchronized (this)
			{
				Set<JComponent> dirtyComponentsForDrawing = repaintSet(window);

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
	void paintDirtyGLComponents(GLG2DFrame frame)
	{
		Set<JComponent> dirtyComponentsForDrawable = repaintSet(frame);

		synchronized (this)
		{
			activeRepaints.clear();

			boolean needsFullRepaint = fullRepaints.get(frame).booleanValue();

			// prevents addDirtyRegion(..) calls from repaints from
			// affecting traversal.
			if (needsFullRepaint)
			{
				activeRepaints.add(frame.getRootPane());
			}
			else
			{
				activeRepaints.addAll(dirtyComponentsForDrawable);
			}

			dirtyComponentsForDrawable.clear();
			fullRepaints.put(frame, false);

			if (activeRepaints.isEmpty())
			{
				return;
			}
		}

		GLGraphics2D graphics = (GLGraphics2D) frame.getGraphics();
		graphics.prePaint(frame.getWindow().getContext());

		paintRecentlyDirtiedComponents();

		graphics.postPaint();
	}

	private Set<JComponent> repaintSet(Window window)
	{
		Set<JComponent> set = dirtyComponents.get(window);

		if (set == null)
		{
			set = new LinkedHashSet<>();
			dirtyComponents.put(window, set);
		}

		return set;
	}

	/**
	 * Paints all of the components that were recently marked dirty.
	 */
	private void paintRecentlyDirtiedComponents()
	{
		Lock paintLock = GLG2DPaintLock.getPaintLock();

		try
		{
			paintLock.lock();

			for (JComponent comp : activeRepaints)
			{
				if (comp instanceof JToolTip)
				{
					// JToolTips don't belong to parent window. Probably
					// need to
					// create a special texture for those.
					continue;
				}

				int w = comp.getWidth();
				int h = comp.getHeight();

				comp.paintImmediately(0, 0, w, h);
			}
		}
		finally
		{
			paintLock.unlock();
		}
	}
}
