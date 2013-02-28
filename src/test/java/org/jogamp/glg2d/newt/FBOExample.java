package org.jogamp.glg2d.newt;

import java.util.concurrent.Executors;

import javax.media.opengl.GL;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;

import org.jogamp.glg2d.GLG2DCanvas;

import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.FBObject;
import com.jogamp.opengl.FBObject.ColorAttachment;
import com.jogamp.opengl.util.Animator;

public class FBOExample
{
	public static void main(String[] args)
	{
		final Animator animator = new Animator();
		animator.setRunAsFastAsPossible(true);

		// onscreen
		GLCapabilities windowCaps = GLG2DCanvas.getDefaultCapabalities();
		GLWindow window = GLWindow.create(windowCaps);

		window.setSize(400, 400);
		window.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowDestroyed(WindowEvent e)
			{
				System.exit(0);
			}
		});
		window.setVisible(true);

		final FBObject fbo = new FBObject();

		window.addGLEventListener(new GLEventListener()
		{
			@Override
			public void init(GLAutoDrawable drawable)
			{
				GL gl = drawable.getGL();

				fbo.reset(gl, drawable.getWidth(), drawable.getHeight());

				ColorAttachment attachment = fbo.createColorAttachment(true);
				fbo.attachColorbuffer(gl, 0, attachment);

				fbo.unbind(gl);
			}

			@Override
			public void display(GLAutoDrawable drawable)
			{
				GL2GL3 gl = drawable.getGL().getGL2GL3();

				fbo.bind(gl);

				gl.glClearColor(1.0f, 0.0f, 0.0f, 1.0f);
				gl.glClear(GL.GL_COLOR_BUFFER_BIT);

				fbo.unbind(gl);

				if (fbo.isInitialized())
				{
					gl.glBindFramebuffer(GL2GL3.GL_READ_FRAMEBUFFER,
					        fbo.getReadFramebuffer());
					gl.glBindFramebuffer(GL2GL3.GL_DRAW_FRAMEBUFFER, 0);

					gl.glBlitFramebuffer(0, 0, fbo.getWidth(), fbo.getHeight(),
					        0, 0, drawable.getWidth(), drawable.getHeight(),
					        GL.GL_COLOR_BUFFER_BIT, GL.GL_NEAREST);
				}
			}

			@Override
			public void reshape(GLAutoDrawable drawable, int x, int y,
			        int width, int height)
			{
				GL gl = drawable.getGL();

				fbo.reset(gl, width, height);
			}

			@Override
			public void dispose(GLAutoDrawable drawable)
			{
				// TODO Auto-generated method stub
			}
		});

		animator.add(window);

		Executors.newSingleThreadExecutor().execute(new Runnable()
		{
			@Override
			public void run()
			{
				animator.start();
			}
		});
	}
}
