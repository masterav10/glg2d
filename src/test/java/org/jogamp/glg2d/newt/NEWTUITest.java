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
 * @author Naval Undersea Warfare Center, Newport RI
 * 
 */
public abstract class NEWTUITest
{
	private static final boolean USE_NIMBUS = false;

	private GLG2DWindow window;

	public void display() throws UnsupportedLookAndFeelException
	{
		display(USE_NIMBUS);
	}

	public void display(boolean b) throws UnsupportedLookAndFeelException
	{
		display(USE_NIMBUS, 400, 400);
	}

	public void display(int width, int height)
	        throws UnsupportedLookAndFeelException
	{
		display(USE_NIMBUS, width, height);
	}

	public void display(boolean useNimbus, int width, int height)
	        throws UnsupportedLookAndFeelException
	{
		final Animator animator = new Animator();
		animator.setRunAsFastAsPossible(true);

		if (useNimbus)
		{
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		}

		GLCapabilities caps = new GLCapabilities(GLProfile.getDefault());
		window = GLG2DWindow.create(caps);

		window.setContentPane(getContentPane());

		window.setSize(width, height);
		window.setVisible(true);
		window.setTitle(this.getClass().getSimpleName());

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
