package org.jogamp.glg2d.newt;

import java.awt.Component;
import java.awt.Window;
import java.awt.peer.KeyboardFocusManagerPeer;

/**
 * 
 * @author Dan Avila
 * 
 */
public class GLG2DWindowKeyboardPeer implements KeyboardFocusManagerPeer
{
	private Component currentFocusOwner;
	private Window currentFocusWindow;

	@Override
	public Window getCurrentFocusedWindow()
	{
		return currentFocusWindow;
	}

	@Override
	public void setCurrentFocusOwner(Component comp)
	{
		this.currentFocusOwner = comp;

		if (comp instanceof Window)
		{
			this.currentFocusWindow = (Window) comp;
		}
	}

	@Override
	public Component getCurrentFocusOwner()
	{
		return currentFocusOwner;
	}

	@Override
	public void clearGlobalFocusOwner(Window activeWindow)
	{
		// TODO:
	}
}
