package org.jogamp.glg2d.newt;

import javax.swing.JComponent;
import javax.swing.JPanel;

// TODO: da: 2/19/2013 - GLG2DWindow does not support frames yet.
// TODO: da: 2/19/2013 - Use JSeparators in this test.
public class MenuTest extends NEWTUITest
{
	@Override
	protected JComponent getContentPane()
	{
		JPanel panel = new JPanel();

		return panel;
	}
}
