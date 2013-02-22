package org.jogamp.glg2d.newt;

import java.util.concurrent.locks.Lock;

import javax.swing.JComponent;

import org.jogamp.glg2d.GLG2DSimpleEventListener;
import org.jogamp.glg2d.GLGraphics2D;

/**
 * This class provides a locking mechanism around the painting method. Because
 * painting is called outside of AWTEventQueue, it is possible for deadlocks to
 * occur between AWT and the animator.
 * 
 * @author Naval Undersea Warfare Center, Newport RI
 * 
 */
public class GLG2DNewtEventListener extends GLG2DSimpleEventListener
{
	public GLG2DNewtEventListener(JComponent component)
	{
		super(component);
	}

	@Override
	protected void paintGL(GLGraphics2D g2d)
	{
		Lock paintLock = GLG2DPaintLock.getPaintLock();

		try
		{
			paintLock.lock();

			super.paintGL(g2d);
		}
		finally
		{
			paintLock.unlock();
		}
	}
}
