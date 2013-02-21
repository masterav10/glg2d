package org.jogamp.glg2d.newt;

import java.awt.Component;
import java.awt.GraphicsConfiguration;
import java.awt.peer.ComponentPeer;

public class GLG2DLightweightPeer7 extends GLG2DLightweightPeer6 {

	public GLG2DLightweightPeer7(Component target) {
		super(target);
		// TODO Auto-generated constructor stub
	}

	public void setZOrder(ComponentPeer above) {
		// TODO Auto-generated method stub
		log();

	}

	public boolean updateGraphicsData(GraphicsConfiguration gc) {
		log();
		// TODO Auto-generated method stub
		return false;
	}
}
