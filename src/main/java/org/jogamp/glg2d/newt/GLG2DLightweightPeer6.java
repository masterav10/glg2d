package org.jogamp.glg2d.newt;

import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.BufferCapabilities.FlipContents;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.PaintEvent;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.VolatileImage;
import java.awt.peer.ContainerPeer;
import java.awt.peer.LightweightPeer;

import sun.awt.CausedFocusEvent.Cause;
import sun.awt.NullComponentPeer;
import sun.java2d.pipe.Region;

/**
 * This is a modified version of the {@link NullComponentPeer} provided by sun.
 * This class implements basic peer functionality.
 * 
 * @author Naval Undersea Warfare Center, Newport RI
 * 
 */
public class GLG2DLightweightPeer6 implements LightweightPeer, ContainerPeer {
	private static final Dimension MIN_SIZE = new Dimension(0, 0);

	private Component target;

	public GLG2DLightweightPeer6(Component target) {
		this.target = target;
	}

	void log() {
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();

		String name = target.getClass().getName();

		// if ("".equals(name.trim()))
		// {
		// name = target.getClass().getName();
		//
		// name = name.substring(name.lastIndexOf('.') + 1);
		// }

		System.err.println(name + ": " + trace[2]);
	}

	public boolean isObscured() {
		log();

		return false;
	}

	public boolean canDetermineObscurity() {
		log();
		// TODO Auto-generated method stub
		return false;
	}

	public void setVisible(boolean v) {
		log();
		// TODO Auto-generated method stub
	}

	public void setEnabled(boolean e) {
		log();
		// TODO Auto-generated method stub

	}

	public void paint(Graphics g) {
		log();
		// TODO Auto-generated method stub

	}

	public void print(Graphics g) {
		log();
		// TODO Auto-generated method stub

	}

	public void setBounds(int x, int y, int width, int height, int op) {
		log();
		// TODO Auto-generated method stub

	}

	public void handleEvent(AWTEvent e) {
		// log();
		// TODO Auto-generated method stub
	}

	public void coalescePaintEvent(PaintEvent e) {
		log();
		// TODO Auto-generated method stub

	}

	public Point getLocationOnScreen() {
		log();
		// TODO Auto-generated method stub
		return null;
	}

	public Dimension getPreferredSize() {
		log();

		return MIN_SIZE;
	}

	public Dimension getMinimumSize() {
		log();

		return MIN_SIZE;
	}

	public ColorModel getColorModel() {
		log();
		// TODO Auto-generated method stub
		return null;
	}

	public Toolkit getToolkit() {
		return Toolkit.getDefaultToolkit();
	}

	public Graphics getGraphics() {
		log();
		// TODO Auto-generated method stub
		return null;
	}

	public FontMetrics getFontMetrics(Font font) {
		log();
		// TODO Auto-generated method stub
		return null;
	}

	public void dispose() {
		log();
		// TODO Auto-generated method stub

	}

	public void setForeground(Color c) {
		// log();
		// TODO Auto-generated method stub

	}

	public void setBackground(Color c) {
		// log();
		// TODO Auto-generated method stub

	}

	public void setFont(Font f) {
		// log();
		// TODO Auto-generated method stub

	}

	public void updateCursorImmediately() {
		log();
		// TODO Auto-generated method stub

	}

	public boolean requestFocus(Component lightweightChild, boolean temporary,
			boolean focusedWindowChangeAllowed, long time, Cause cause) {
		// TODO Auto-generated method stub
		log();
		return false;
	}

	public boolean isFocusable() {
		// TODO Auto-generated method stub
		log();
		return false;
	}

	public Image createImage(ImageProducer producer) {
		// TODO Auto-generated method stub
		log();
		return null;
	}

	public Image createImage(int width, int height) {
		// TODO Auto-generated method stub
		log();
		return null;
	}

	public VolatileImage createVolatileImage(int width, int height) {
		// TODO Auto-generated method stub
		log();
		return null;
	}

	public boolean prepareImage(Image img, int w, int h, ImageObserver o) {
		// TODO Auto-generated method stub
		log();
		return false;
	}

	public int checkImage(Image img, int w, int h, ImageObserver o) {
		// TODO Auto-generated method stub
		log();
		return 0;
	}

	public GraphicsConfiguration getGraphicsConfiguration() {
		// TODO Auto-generated method stub
		log();
		return null;
	}

	public boolean handlesWheelScrolling() {
		// TODO Auto-generated method stub
		log();
		return false;
	}

	public void createBuffers(int numBuffers, BufferCapabilities caps)
			throws AWTException {
		// TODO Auto-generated method stub
		log();

	}

	public Image getBackBuffer() {
		// TODO Auto-generated method stub
		log();
		return null;
	}

	public void flip(int x1, int y1, int x2, int y2, FlipContents flipAction) {
		// TODO Auto-generated method stub
		log();

	}

	public void destroyBuffers() {
		// TODO Auto-generated method stub
		log();

	}

	public void reparent(ContainerPeer newContainer) {
		// TODO Auto-generated method stub
		log();

	}

	public boolean isReparentSupported() {
		// TODO Auto-generated method stub
		log();
		return false;
	}

	public void layout() {
		// TODO Auto-generated method stub
		log();

	}

	public void applyShape(Region shape) {
		// TODO Auto-generated method stub
		log();

	}

	public void disable() {
		// TODO Auto-generated method stub

	}

	public void enable() {
		// TODO Auto-generated method stub

	}

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	public void hide() {
		// TODO Auto-generated method stub

	}

	public Dimension minimumSize() {
		// TODO Auto-generated method stub
		return null;
	}

	public Dimension preferredSize() {
		// TODO Auto-generated method stub
		return null;
	}

	public void repaint(long arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub

	}

	public void reshape(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	public void show() {
		// TODO Auto-generated method stub

	}

	public void beginLayout() {
		// TODO Auto-generated method stub

	}

	public void beginValidate() {
		// TODO Auto-generated method stub

	}

	public void endLayout() {
		// TODO Auto-generated method stub

	}

	public void endValidate() {
		// TODO Auto-generated method stub

	}

	public Insets getInsets() {
		// TODO Auto-generated method stub
		return null;
	}

	public Insets insets() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isPaintPending() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isRestackSupported() {
		// TODO Auto-generated method stub
		return false;
	}

	public void restack() {
		// TODO Auto-generated method stub

	}
}
