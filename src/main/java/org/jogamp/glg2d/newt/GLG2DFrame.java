package org.jogamp.glg2d.newt;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JViewport;

import org.jogamp.glg2d.GLG2DCanvas;
import org.jogamp.glg2d.GLGraphics2D;

import com.jogamp.newt.opengl.GLWindow;

class GLG2DFrame extends JFrame
{
	private static final long serialVersionUID = 8999015711459748410L;

	private GLWindow window;
	private GLGraphics2D g;

	public GLG2DFrame(GLWindow window)
	{
		this.window = window;

		setUndecorated(true);
		setIgnoreRepaint(true);
	}

	public GLWindow getWindow()
	{
		return window;
	}

	@Override
	public void addNotify()
	{
		verifyHierarchy(this);

		super.addNotify();
	}

	@Override
	public GLGraphics2D getGraphics()
	{
		return g;
	}

	/**
	 * Sets the current graphics. This method must be called from a GLMethod.
	 * 
	 * @param g
	 *            - the graphics. If null, then the current graphics will be
	 *            disposed.
	 */
	void setGraphics(GLGraphics2D g)
	{
		if (g == null && this.g != null)
		{
			this.g.dispose();
		}

		this.g = g;
	}

	/**
	 * 
	 * @param comp
	 * @see GLG2DCanvas#verifyHierarchy(Component comp)
	 */
	protected void verifyHierarchy(Component comp)
	{
		if (comp instanceof JViewport)
		{
			((JViewport) comp).setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		}

		if (comp instanceof Container)
		{
			Container cont = (Container) comp;
			for (int i = 0; i < cont.getComponentCount(); i++)
			{
				verifyHierarchy(cont.getComponent(i));
			}
		}
	}
}
