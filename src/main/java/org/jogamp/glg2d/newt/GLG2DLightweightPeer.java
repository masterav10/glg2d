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
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.PaintEvent;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.VolatileImage;
import java.awt.peer.ComponentPeer;
import java.awt.peer.ContainerPeer;
import java.awt.peer.LightweightPeer;

import sun.awt.CausedFocusEvent.Cause;
import sun.awt.NullComponentPeer;
import sun.java2d.pipe.Region;

/**
 * This is a modified version of the {@link NullComponentPeer} provided by sun.
 * This class implements basic peer functionality.
 * 
 * @author Dan Avila
 */
public class GLG2DLightweightPeer implements LightweightPeer
{
	private static final Dimension MIN_SIZE = new Dimension(0, 0);

	private Component target;

	public GLG2DLightweightPeer(Component target)
	{
		this.target = target;
	}

	@Override
	public boolean isObscured()
	{

		return false;
	}

	@Override
	public boolean canDetermineObscurity()
	{

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setVisible(boolean v)
	{

		// TODO Auto-generated method stub
	}

	@Override
	public void setEnabled(boolean e)
	{

		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g)
	{

		// TODO Auto-generated method stub

	}

	@Override
	public void print(Graphics g)
	{

		// TODO Auto-generated method stub

	}

	@Override
	public void setBounds(int x, int y, int width, int height, int op)
	{

		// TODO Auto-generated method stub

	}

	@Override
	public void handleEvent(AWTEvent e)
	{
		//
		// TODO Auto-generated method stub
	}

	@Override
	public void coalescePaintEvent(PaintEvent e)
	{

		// TODO Auto-generated method stub

	}

	@Override
	public Point getLocationOnScreen()
	{

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension getPreferredSize()
	{

		return MIN_SIZE;
	}

	@Override
	public Dimension getMinimumSize()
	{

		return MIN_SIZE;
	}

	@Override
	public ColorModel getColorModel()
	{

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Toolkit getToolkit()
	{
		return Toolkit.getDefaultToolkit();
	}

	@Override
	public Graphics getGraphics()
	{

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FontMetrics getFontMetrics(Font font)
	{

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dispose()
	{

		// TODO Auto-generated method stub

	}

	@Override
	public void setForeground(Color c)
	{
		//
		// TODO Auto-generated method stub

	}

	@Override
	public void setBackground(Color c)
	{
		//
		// TODO Auto-generated method stub

	}

	@Override
	public void setFont(Font f)
	{
		//
		// TODO Auto-generated method stub

	}

	@Override
	public void updateCursorImmediately()
	{

		// TODO Auto-generated method stub

	}

	@Override
	public boolean requestFocus(Component lightweightChild, boolean temporary,
	        boolean focusedWindowChangeAllowed, long time, Cause cause)
	{
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public boolean isFocusable()
	{
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public Image createImage(ImageProducer producer)
	{
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public Image createImage(int width, int height)
	{
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public VolatileImage createVolatileImage(int width, int height)
	{
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public boolean prepareImage(Image img, int w, int h, ImageObserver o)
	{
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public int checkImage(Image img, int w, int h, ImageObserver o)
	{
		// TODO Auto-generated method stub

		return 0;
	}

	@Override
	public GraphicsConfiguration getGraphicsConfiguration()
	{
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public boolean handlesWheelScrolling()
	{
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public void createBuffers(int numBuffers, BufferCapabilities caps)
	        throws AWTException
	{
		// TODO Auto-generated method stub

	}

	@Override
	public Image getBackBuffer()
	{
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void flip(int x1, int y1, int x2, int y2, FlipContents flipAction)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void destroyBuffers()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void reparent(ContainerPeer newContainer)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isReparentSupported()
	{
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public void layout()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void applyShape(Region shape)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setZOrder(ComponentPeer above)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean updateGraphicsData(GraphicsConfiguration gc)
	{

		// TODO Auto-generated method stub
		return false;
	}
}
