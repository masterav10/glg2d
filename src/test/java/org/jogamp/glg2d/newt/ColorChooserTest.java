package org.jogamp.glg2d.newt;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * A simple color chooser.
 * 
 * @author Naval Undersea Warfare Center, Newport RI
 * 
 */
public class ColorChooserTest extends NEWTUITest
{
	@Override
	protected JComponent getContentPane()
	{
		JColorChooser chooser = new JColorChooser();

		return chooser;
	}

	public static void main(String[] args)
	        throws UnsupportedLookAndFeelException
	{
		new ColorChooserTest().display(600, 400);
	}
}
