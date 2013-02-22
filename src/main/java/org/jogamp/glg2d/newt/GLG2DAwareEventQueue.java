package org.jogamp.glg2d.newt;

import java.awt.AWTEvent;
import java.awt.EventQueue;
import java.util.concurrent.locks.Lock;

/**
 * This {@link EventQueue} will not fire events unless GLG2D is not drawing.
 * 
 * @author Dan Avila
 */
public class GLG2DAwareEventQueue extends EventQueue
{
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * Locks on the {@link GLG2DPaintLock} before proceeding.
	 * </p>
	 */
	@Override
	protected void dispatchEvent(AWTEvent event)
	{
		Lock paintLock = GLG2DPaintLock.getPaintLock();

		try
		{
			paintLock.lock();

			super.dispatchEvent(event);
		}
		finally
		{
			paintLock.unlock();
		}
	}
}
