package org.jogamp.glg2d.newt;

import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.BufferCapabilities.FlipContents;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MenuBar;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.PaintEvent;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.VolatileImage;
import java.awt.peer.ComponentPeer;
import java.awt.peer.ContainerPeer;
import java.awt.peer.FramePeer;

import javax.media.nativewindow.util.InsetsImmutable;

import sun.awt.CausedFocusEvent.Cause;
import sun.java2d.pipe.Region;

import com.jogamp.newt.opengl.GLWindow;

public class GLG2DWindowPeer implements FramePeer {
	private static final Dimension MIN_SIZE = new Dimension(0, 0);
	private GLG2DFrame frame;

	public GLG2DWindowPeer(Frame target) {
		if (target instanceof GLG2DFrame) {
			frame = (GLG2DFrame) target;
		} else {
			throw new IllegalArgumentException(
					"Frame must be a GLG2DFrame when using this toolkit.");
		}
	}

	public void toFront() {
		// TODO Auto-generated method stub

	}

	public void toBack() {
		// TODO Auto-generated method stub

	}

	public void setAlwaysOnTop(boolean alwaysOnTop) {
		// TODO Auto-generated method stub

	}

	public void updateFocusableWindowState() {
		// TODO Auto-generated method stub

	}

	public void setModalBlocked(Dialog blocker, boolean blocked) {
		// TODO Auto-generated method stub

	}

	public void updateMinimumSize() {
		// TODO Auto-generated method stub

	}

	public void updateIconImages() {
		// TODO Auto-generated method stub

	}

	public void setOpacity(float opacity) {
		// TODO Auto-generated method stub

	}

	public void setOpaque(boolean isOpaque) {
		// TODO Auto-generated method stub

	}

	public void updateWindow() {
		// TODO Auto-generated method stub

	}

	public void repositionSecurityWarning() {
		// TODO Auto-generated method stub

	}

	public Insets getInsets() {
		GLWindow window = frame.getWindow();

		InsetsImmutable glinsets = window.getInsets();

		Insets insets = new Insets(glinsets.getTopHeight(),
				glinsets.getLeftWidth(), glinsets.getBottomHeight(),
				glinsets.getRightWidth());

		return insets;
	}

	public void beginValidate() {
		// TODO Auto-generated method stub

	}

	public void endValidate() {
		// TODO Auto-generated method stub

	}

	public void beginLayout() {
		// TODO Auto-generated method stub

	}

	public void endLayout() {
		// TODO Auto-generated method stub

	}

	public boolean isObscured() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean canDetermineObscurity() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setVisible(boolean v) {
		// TODO Auto-generated method stub

	}

	public void setEnabled(boolean e) {
		// TODO Auto-generated method stub

	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub

	}

	public void print(Graphics g) {
		// TODO Auto-generated method stub

	}

	public void setBounds(int x, int y, int width, int height, int op) {
		// TODO Auto-generated method stub

	}

	public void handleEvent(AWTEvent e) {
		// TODO Auto-generated method stub

	}

	public void coalescePaintEvent(PaintEvent e) {
		// TODO Auto-generated method stub

	}

	private Point awtTempPoint = new Point();

	public Point getLocationOnScreen() {
		GLWindow window = frame.getWindow();

		awtTempPoint.x = window.getX();
		awtTempPoint.y = window.getY();

		return awtTempPoint;
	}

	private Dimension sizeCache = new Dimension();

	public Dimension getPreferredSize() {
		GLWindow window = frame.getWindow();

		sizeCache.setSize(window.getWidth(), window.getHeight());

		return sizeCache;
	}

	public Dimension getMinimumSize() {
		return MIN_SIZE;
	}

	public ColorModel getColorModel() {
		// TODO Auto-generated method stub
		return null;
	}

	public Toolkit getToolkit() {
		return Toolkit.getDefaultToolkit();
	}

	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return null;
	}

	public FontMetrics getFontMetrics(Font font) {
		// TODO Auto-generated method stub
		return null;
	}

	public void dispose() {
		// TODO Auto-generated method stub

	}

	public void setForeground(Color c) {
		// TODO Auto-generated method stub

	}

	public void setBackground(Color c) {
		// TODO Auto-generated method stub

	}

	public void setFont(Font f) {
		// TODO Auto-generated method stub

	}

	public void updateCursorImmediately() {
		// TODO Auto-generated method stub

	}

	public boolean requestFocus(Component lightweightChild, boolean temporary,
			boolean focusedWindowChangeAllowed, long time, Cause cause) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isFocusable() {
		// TODO Auto-generated method stub
		return false;
	}

	public Image createImage(ImageProducer producer) {
		// TODO Auto-generated method stub
		return null;
	}

	public Image createImage(int width, int height) {
		// TODO Auto-generated method stub
		return null;
	}

	public VolatileImage createVolatileImage(int width, int height) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean prepareImage(Image img, int w, int h, ImageObserver o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int checkImage(Image img, int w, int h, ImageObserver o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public GraphicsConfiguration getGraphicsConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean handlesWheelScrolling() {
		// TODO Auto-generated method stub
		return false;
	}

	public void createBuffers(int numBuffers, BufferCapabilities caps)
			throws AWTException {
		// TODO Auto-generated method stub

	}

	public Image getBackBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

	public void flip(int x1, int y1, int x2, int y2, FlipContents flipAction) {
		// TODO Auto-generated method stub

	}

	public void destroyBuffers() {
		// TODO Auto-generated method stub

	}

	public void reparent(ContainerPeer newContainer) {
		// TODO Auto-generated method stub

	}

	public boolean isReparentSupported() {
		// TODO Auto-generated method stub
		return false;
	}

	public void layout() {
		// TODO Auto-generated method stub

	}

	public void applyShape(Region shape) {
		// TODO Auto-generated method stub

	}

	public void setTitle(String title) {
		// TODO Auto-generated method stub

	}

	public void setMenuBar(MenuBar mb) {
		// TODO Auto-generated method stub

	}

	public void setResizable(boolean resizeable) {
		// TODO Auto-generated method stub

	}

	public void setState(int state) {
		// TODO Auto-generated method stub

	}

	public int getState() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setMaximizedBounds(Rectangle bounds) {
		// TODO Auto-generated method stub

	}

	public void setBoundsPrivate(int x, int y, int width, int height) {
		// TODO Auto-generated method stub

	}

	public Rectangle getBoundsPrivate() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean requestWindowFocus() {
		// TODO Auto-generated method stub
		return false;
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
}
