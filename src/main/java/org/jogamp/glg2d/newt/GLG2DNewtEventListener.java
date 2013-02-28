package org.jogamp.glg2d.newt;

import java.util.concurrent.locks.Lock;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.swing.JComponent;

import org.jogamp.glg2d.GLG2DSimpleEventListener;
import org.jogamp.glg2d.GLGraphics2D;

/**
 * This class provides a locking mechanism around the painting method. Because
 * painting is called outside of AWTEventQueue, it is possible for deadlocks to
 * occur between AWT and the animator.
 * 
 * @author Dan Avila
 * 
 */
public class GLG2DNewtEventListener extends GLG2DSimpleEventListener
{
	public GLG2DNewtEventListener(JComponent component)
	{
		super(component);
	}

	@Override
	public void init(GLAutoDrawable drawable)
	{
		GL gl = drawable.getGL();

		gl.setSwapInterval(1);

		super.init(drawable);
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
