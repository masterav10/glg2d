package org.jogamp.glg2d.newt;

import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Demonstrates internal frames in NEWT using GLG2D.
 * 
 * @author Naval Undersea Warfare Center, Newport RI
 * 
 */
public class InternalFrameTest extends NEWTUITest
{
	@Override
	protected JComponent getContentPane()
	{
		JDesktopPane panel = new JDesktopPane();

		panel.add(create("Internal Frame 1"));
		panel.add(create("Internal Frame 2"));

		return panel;
	}

	private JInternalFrame create(String title)
	{
		JInternalFrame frame = new JInternalFrame(title);
		frame.setVisible(true);
		frame.setSize(300, 300);

		return frame;
	}

	public static void main(String[] args)
	        throws UnsupportedLookAndFeelException
	{
		new InternalFrameTest().display();
	}

}
