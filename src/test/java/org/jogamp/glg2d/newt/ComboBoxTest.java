package org.jogamp.glg2d.newt;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This test demonstrates combo boxes in GLG2D.
 * 
 * @author Naval Undersea Warfare Center, Newport RI
 * 
 */
public class ComboBoxTest extends NEWTUITest
{
	@Override
	protected JComponent getContentPane()
	{
		String[] data =
		{ "ONE", "TWO", "THREE", "FOUR", "FIVE" };

		JPanel panel = new JPanel(new GridLayout(0, 1));

		JComboBox<String> comboBox = new JComboBox<String>(data);
		panel.add(comboBox);

		JComboBox<String> editableBox = new JComboBox<String>(data);
		editableBox.setEditable(true);
		panel.add(editableBox);

		return panel;
	}

	public static void main(String[] args)
	        throws UnsupportedLookAndFeelException
	{
		new ComboBoxTest().display();
	}
}
