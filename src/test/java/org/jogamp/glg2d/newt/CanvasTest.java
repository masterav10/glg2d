package org.jogamp.glg2d.newt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.media.opengl.GL;
import javax.media.opengl.GLContext;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Demonstrates a raw component that draw's OpenGL stuff.
 * 
 * @author Naval Undersea Warfare Center, Newport RI
 * 
 */
public class CanvasTest extends NEWTUITest
{
	private boolean flip = false;

	@Override
	protected JComponent getContentPane()
	{
		JPanel panel = new JPanel(new GridLayout(2, 2));

		panel.add(bluePanel());

		JComponent canvas = new JComponent()
		{
			private static final long serialVersionUID = -6606967802486457192L;

			@Override
			protected void paintComponent(Graphics g)
			{
				GL gl = GLContext.getCurrentGL();

				float c = flip ? 1.0f : 0.0f;

				flip = !flip;

				gl.glClearColor(c, c, c, 1.0f);
				gl.glClear(GL.GL_COLOR_BUFFER_BIT);
			}
		};

		canvas.setLayout(new GridLayout(2, 1));
		canvas.add(new JButton("Hello"));

		panel.add(canvas);

		panel.add(bluePanel());
		panel.add(bluePanel());

		return panel;
	}

	private JPanel bluePanel()
	{
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLUE);

		return panel;
	}

	public static void main(String[] args)
	        throws UnsupportedLookAndFeelException
	{
		new CanvasTest().display();
	}
}
