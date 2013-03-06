package org.jogamp.glg2d.newt;

import java.util.concurrent.Executors;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.opengl.util.Animator;

/**
 * Base class that provides the implementation for all NEWT UI test cases.
 * 
 * If you need to add GL acceleration, the drawable is available through
 * {@link #getDrawable()}.
 * 
 * Make sure you run tests in here with:
 * 
 * <pre>
 * -Dawt.toolkit=org.jogamp.glg2d.newt.GLG2DWindowToolkit
 * </pre>
 * 
 * @author Dan Avila
 * 
 */
public abstract class NEWTUITest
{
	private GLG2DWindow window;
	private int width = 400;
	private int height = 400;
	private boolean useNimbus = false;
	private boolean fullScreen = false;
	private boolean undecorated;

	public NEWTUITest setSize(int width, int height)
	{
		this.width = width;
		this.height = height;

		return this;
	}

	public NEWTUITest setFullScreen(boolean fullScreen)
	{
		this.fullScreen = fullScreen;

		return this;
	}

	public NEWTUITest setNimbus(boolean useNimbus)
	{
		this.useNimbus = useNimbus;

		return this;
	}

	public NEWTUITest setUndecorated(boolean undecorated)
	{
		this.undecorated = undecorated;

		return this;
	}

	/**
	 * @see #setFullScreen(boolean)
	 * @see #setNimbus(boolean)
	 * @see #setSize(int, int)
	 */
	public void display()
	{
		final Animator animator = new Animator();
		animator.setRunAsFastAsPossible(true);

		if (useNimbus)
		{
			try
			{
				UIManager.setLookAndFeel(new NimbusLookAndFeel());
			}
			catch (UnsupportedLookAndFeelException e1)
			{
				e1.printStackTrace();
			}
		}

		GLCapabilities caps = new GLCapabilities(GLProfile.getDefault());
		window = GLG2DWindow.create(caps);

		window.setContentPane(getContentPane());

		window.setUndecorated(undecorated);
		window.setTitle(this.getClass().getSimpleName());
		window.setSize(width, height);
		window.setVisible(true);
		window.setFullscreen(fullScreen);

		window.addWindowListener(new WindowAdapter()
		{
			public void windowDestroyed(WindowEvent e)
			{
				System.exit(0);
			}
		});

		animator.add(window.getDrawable());
		Executors.newSingleThreadExecutor().execute(new Runnable()
		{
			public void run()
			{
				animator.start();
			}
		});
	}

	public String getName()
	{
		return this.getClass().getSimpleName();
	}

	/**
	 * This method should return a component that will be used as the content
	 * pane for the provided window.
	 * 
	 * @return the content pane.
	 */
	protected abstract JComponent getContentPane();

	protected GLAutoDrawable getDrawable()
	{
		return window.getDrawable();
	}
}
