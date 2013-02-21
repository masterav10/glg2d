package org.jogamp.glg2d.newt;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.peer.LightweightPeer;

import sun.awt.KeyboardFocusManagerPeerProvider;

public class GLG2DWindowToolkit7 extends GLG2DWindowToolkit6 implements
		KeyboardFocusManagerPeerProvider {

	@Override
	public KeyboardFocusManagerPeer createKeyboardFocusManagerPeer(
			KeyboardFocusManager arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected LightweightPeer createComponent(Component target) {
		LightweightPeer peer = new GLG2DLightweightPeer7(target);

		return peer;
	}
}
