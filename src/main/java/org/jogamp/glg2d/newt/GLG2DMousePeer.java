package org.jogamp.glg2d.newt;

import java.awt.Point;
import java.awt.Window;
import java.awt.peer.MouseInfoPeer;

/**
 * Basic stub to prevent an exception from being thrown.
 * 
 * @author Dan Avila
 * 
 */
public class GLG2DMousePeer implements MouseInfoPeer
{
	@Override
	public int fillPointWithCoords(Point point)
	{
		return 0;
	}

	@Override
	public boolean isWindowUnderMouse(Window w)
	{
		return false;
	}

}
