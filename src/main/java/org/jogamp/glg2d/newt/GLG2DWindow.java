package org.jogamp.glg2d.newt;

import java.awt.Toolkit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilitiesImmutable;
import javax.swing.JComponent;

import org.jogamp.glg2d.GLG2DHeadlessListener;
import org.jogamp.glg2d.GLG2DSimpleEventListener;
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
public class GLG2DWindow {
	private static final String TOOLKIT_KEY = "awt.toolkit";

	/**
	 * This map holds toolkits in descending order. Because the API is subject
	 * to change over time, it is necessary to do this. The version closest to
	 * the current runtime will be specified.
	 */
	private static final Map<String, Class<?>> TOOLKIT_CLASSES = new LinkedHashMap<String, Class<?>>();

	static {
		TOOLKIT_CLASSES.put("1.7", GLG2DWindowToolkit7.class);
		TOOLKIT_CLASSES.put("1.6", GLG2DWindowToolkit6.class);
	}

	private GLG2DSimpleEventListener painterListener;
	private GLG2DHeadlessListener reshapeListener;
	private NewtMouseEventTranslator evtListener;

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
	protected GLG2DWindow(GLWindow window) {
		this.window = window;

		container = new GLG2DFrame(window);
	}

	/**
	 * This method provides access to the drawable used.
	 * 
	 * @return the accelerated drawable.
	 */
	public GLAutoDrawable getDrawable() {
		return window;
	}

	/**
	 * Changes the content pane of this window. Does nothing if "component" is
	 * null.
	 * 
	 * @param component
	 *            - the new content pane.
	 */
	public void setContentPane(JComponent component) {
		if (component != null) {
			this.container.setContentPane(component);

			if (window.isVisible()) {
				// add notify has already been called if the window is visible
				this.container.verifyHierarchy(component);
			}

			window.removeGLEventListener(painterListener);
			window.removeGLEventListener(reshapeListener);
			painterListener = new GLG2DSimpleEventListener(
					container.getRootPane());
			reshapeListener = new GLG2DHeadlessListener(container.getRootPane());
			window.addGLEventListener(painterListener);
			window.addGLEventListener(reshapeListener);

			window.removeMouseListener(evtListener);
			this.evtListener = new NewtMouseEventTranslator(component);
			window.addMouseListener(evtListener);
		}
	}

	/**
	 * The frame becomes visible once set here.
	 * 
	 * @param visible
	 *            - true to show the window, false otherwise.
	 */
	public void setVisible(boolean visible) {
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
	public static GLG2DWindow create(GLCapabilitiesImmutable caps) {

		System.setProperty(TOOLKIT_KEY,
				"org.jogamp.glg2d.newt.GLG2DWindowToolkit");

		// TODO: Not sure if this property is available on all systems /
		// platforms.
		String version = System.getProperty("java.version");

		Class<?> toolkitClass = null;

		for (Entry<String, Class<?>> toolkitEntry : TOOLKIT_CLASSES.entrySet()) {
			String currentVersion = toolkitEntry.getKey();

			if (currentVersion.startsWith(version)
					|| currentVersion.compareTo(version) <= 0) {
				toolkitClass = toolkitEntry.getValue();
				break;
			}
		}

		if (toolkitClass == null
				|| !toolkitClass.isInstance(Toolkit.getDefaultToolkit())) {
			String sep = System.getProperty("line.separator");

			throw new IllegalStateException(
					"GLG2DWindow requires a special toolkit to work. Use "
							+ sep + "-Dawt.toolkit=" + toolkitClass.getName());
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
	public void setSize(int width, int height) {
		window.setSize(width, height);
	}

	/**
	 * Removes the decoration toolbar from this window.
	 * 
	 * @param value
	 *            - true if decorations should be removed, false otherwise.
	 */
	public void setUndecorated(boolean value) {
		window.setUndecorated(value);
	}

	/**
	 * The title for this window.
	 * 
	 * @param title
	 *            - the title.
	 */
	public void setTitle(String title) {
		window.setTitle(title);
	}

	public void addWindowListener(WindowListener listener) {
		window.addWindowListener(listener);
	}
}
