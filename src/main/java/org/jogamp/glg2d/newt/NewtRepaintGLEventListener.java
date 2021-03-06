package org.jogamp.glg2d.newt;

import java.util.concurrent.locks.Lock;

import javax.media.opengl.GL;
import javax.media.opengl.GL2GL3;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.swing.JComponent;
import javax.swing.JRootPane;

import org.jogamp.glg2d.GLGraphics2D;

import com.jogamp.opengl.FBObject;
import com.jogamp.opengl.FBObject.ColorAttachment;

/**
 * This event listener is attached to a {@link GLG2DWindow}, and handles the
 * painting of the {@link GLG2DWindow#setContentPane(javax.swing.JComponent)
 * content pane}.
 * 
 * @author Dan Avila
 */
public class NewtRepaintGLEventListener implements GLEventListener
{
	private FBObject fbo = new FBObject();
	private boolean needsFullPaint;
	private GLG2DFrame frame;

	public NewtRepaintGLEventListener(GLG2DFrame frame)
	{
		this.frame = frame;
	}

	@Override
	public void init(GLAutoDrawable drawable)
	{
		// basic initialization

		frame.setGraphics(new GLGraphics2D());
		frame.addNotify();

		GL gl = drawable.getGL();

		fbo.reset(gl, drawable.getWidth(), drawable.getHeight());

		ColorAttachment attachment = fbo.createColorAttachment(true);
		fbo.attachColorbuffer(gl, 0, attachment);

		fbo.unbind(gl);

		needsFullPaint = true;
	}

	@Override
	public void display(GLAutoDrawable drawable)
	{
		// TODO: make this more backwards compatible with GL2 and GL2ES2.
		GL2GL3 gl = drawable.getGL().getGL2GL3();

		fbo.bind(gl);

		Lock paintLock = GLG2DPaintLock.getPaintLock();

		try
		{
			paintLock.lock();

			GLGraphics2D graphics = frame.getGraphics();
			graphics.prePaint(drawable.getContext());

			JComponent comp = frame.getRootPane();

			if (needsFullPaint)
			{
				comp.paint(graphics);

				needsFullPaint = true;
			}
			else
			{
				// TODO: GLDeletgatedGraphics??
			}
		}
		finally
		{
			paintLock.unlock();
		}

		fbo.unbind(gl);

		gl.glBindFramebuffer(GL2GL3.GL_READ_FRAMEBUFFER,
		        fbo.getReadFramebuffer());
		gl.glBindFramebuffer(GL2GL3.GL_DRAW_FRAMEBUFFER, 0);

		int fboW = fbo.getWidth();
		int fboH = fbo.getHeight();
		int dW = drawable.getWidth();
		int dH = drawable.getHeight();
		int mask = GL.GL_COLOR_BUFFER_BIT;
		int filter = GL.GL_NEAREST;

		gl.glBlitFramebuffer(0, 0, fboW, fboH, 0, 0, dW, dH, mask, filter);

		gl.glBindFramebuffer(GL2GL3.GL_READ_BUFFER, 0);
	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
	        int height)
	{
		needsFullPaint = true;

		GL gl = drawable.getGL();

		fbo.reset(gl, width, height);

		JRootPane pane = frame.getRootPane();
		pane.setSize(width, height);
		pane.validate();
	}

	@Override
	public void dispose(GLAutoDrawable drawable)
	{
		GL gl = drawable.getGL();

		fbo.destroy(gl);

		frame.setGraphics(null);
	}

	/**
	 * The backing store for the Swing UI.
	 * 
	 * @return an FBO with the latest Swing UI painted to it.
	 */
	public FBObject getFBO()
	{
		return fbo;
	}
}
