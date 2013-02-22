package org.jogamp.glg2d.newt;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class provides a common lock to prevent the event queue and drawing
 * listeners from executing at the same (otherwise, there will be a deadlock).
 * 
 * @author Naval Undersea Warfare Center, Newport RI
 * 
 */
final class GLG2DPaintLock
{
	private static final Lock lock = new ReentrantLock();

	/**
	 * Use this lock to wait for painting to complete.
	 * 
	 * @return the paint lock.
	 */
	public static Lock getPaintLock()
	{
		return lock;
	}
}
