package org.jogamp.glg2d.newt;

import java.awt.Toolkit;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilitiesImmutable;
import javax.media.opengl.GLEventListener;
import javax.swing.JComponent;

import org.jogamp.glg2d.event.NewtKeyEventTranslator;
import org.jogamp.glg2d.event.NewtMouseEventTranslator;

import com.jogamp.newt.event.WindowListener;
import com.jogamp.newt.opengl.GLWindow;

/**
 * An extension to the GLWindow class that can handle rendering
 * {@link #setRootPane(JComponent) Swing components}.
 * 
 * Check the <i>See Also</i> section for additional ways to create canvases.
 * 
 * @author Dan Avila
 * 
 * @see #create(GLCapabilitiesImmutable)
 */
public class GLG2DWindow
{
	private static final String TOOLKIT_KEY = "awt.toolkit";
	private static final Class<?> TOOLKIT_CLASS = GLG2DWindowToolkit.class;

	private GLEventListener painterListener;
	private NewtMouseEventTranslator evtMouseListener;
	private NewtKeyEventTranslator evtKeyListener;

	private GLWindow window;
	private GLG2DFrame container;

	/**
	 * Creates a new GLG2DWindow.
	 * 
	 * Registers an handler that will forward mouse, keyboard, and window events
	 * to the {@link #getContentPane() content pane}.
	 * 
	 * @param window
	 *            - the native window implementation.
	 */
	protected GLG2DWindow(GLWindow window)
	{
		this.window = window;

		container = new GLG2DFrame(window);
	}

	/**
	 * This method provides access to the drawable used.
	 * 
	 * @return the accelerated drawable.
	 */
	public GLAutoDrawable getDrawable()
	{
		return window;
	}

	/**
	 * Changes the content pane of this window. Does nothing if "component" is
	 * null.
	 * 
	 * @param component
	 *            - the new content pane.
	 */
	public void setContentPane(JComponent component)
	{
		if (component != null)
		{
			this.container.setContentPane(component);

			if (window.isVisible())
			{
				// add notify has already been called if the window is visible
				this.container.verifyHierarchy(component);
			}

			window.removeGLEventListener(painterListener);
			painterListener = new NewtRepaintGLEventListener(container);
			window.addGLEventListener(painterListener);

			window.removeMouseListener(evtMouseListener);
			window.removeKeyListener(evtKeyListener);
			this.evtMouseListener = new NewtMouseEventTranslator(component);
			this.evtKeyListener = new NewtKeyEventTranslator(component);
			window.addMouseListener(evtMouseListener);
			window.addKeyListener(evtKeyListener);
		}
	}

	/**
	 * The frame becomes visible once set here.
	 * 
	 * @param visible
	 *            - true to show the window, false otherwise.
	 */
	public void setVisible(boolean visible)
	{
		container.setVisible(visible);
		window.setVisible(visible);
	}

	/**
	 * Creates a new {@link GLG2DWindow}. This method will attempt to set the
	 * {@link Toolkit} to the correct implementation, and will fail if the
	 * toolkit has already been set.
	 * 
	 * @param caps
	 *            - the requested GL capabilities of the provided window.
	 * @return the new window.
	 * @throws IllegalStateException
	 *             if the correct toolkit is not used.
	 */
	public static GLG2DWindow create(GLCapabilitiesImmutable caps)
	{
		System.setProperty(TOOLKIT_KEY,
		        "org.jogamp.glg2d.newt.GLG2DWindowToolkit");

		if (!TOOLKIT_CLASS.isInstance(Toolkit.getDefaultToolkit()))
		{
			String sep = System.getProperty("line.separator");

			throw new IllegalStateException(
			        "GLG2DWindow requires a special toolkit to work. Use "
			                + sep + "-Dawt.toolkit="
			                + GLG2DWindowToolkit.class.getName());
		}

		GLWindow window = GLWindow.create(caps);

		return new GLG2DWindow(window);
	}

	/**
	 * Set the size of the window.
	 * 
	 * @param width
	 *            - the width of the window.
	 * @param height
	 *            - the height of the window.
	 */
	public void setSize(int width, int height)
	{
		container.setSize(width, height);
		window.setSize(width, height);
	}

	/**
	 * Removes the decoration toolbar from this window.
	 * 
	 * @param value
	 *            - true if decorations should be removed, false otherwise.
	 */
	public void setUndecorated(boolean value)
	{
		container.setUndecorated(value);
		window.setUndecorated(value);
	}

	/**
	 * Enters this window in fullscreen mode. Passing false to this method will
	 * remove the window from fullscreen.
	 * 
	 * @param fullscreen
	 *            - true if this window should go fullscreen.
	 */
	public void setFullscreen(boolean fullscreen)
	{
		window.setFullscreen(fullscreen);
	}

	/**
	 * The title for this window.
	 * 
	 * @param title
	 *            - the title.
	 */
	public void setTitle(String title)
	{
		container.setTitle(title);
		window.setTitle(title);
	}

	public void addWindowListener(WindowListener listener)
	{
		window.addWindowListener(listener);
	}
}
