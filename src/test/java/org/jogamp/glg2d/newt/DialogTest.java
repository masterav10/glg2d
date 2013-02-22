package org.jogamp.glg2d.newt;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This test provides a series of buttons that bring up dialogs for various
 * purposes.
 * 
 * @author Dan Avila
 * 
 */
public class DialogTest extends NEWTUITest
{
	private JLabel output = new JLabel();

	@Override
	protected JComponent getContentPane()
	{
		JPanel panel = new JPanel(new GridLayout(2, 2));

		panel.add(button("Save", new SaveFileDialogAction()));
		panel.add(button("Open", new OpenFileDialogAction()));
		panel.add(button("Color Chooser", new ColorDialogAction()));

		return panel;
	}

	private JButton button(String name, ActionListener action)
	{
		JButton button = new JButton(name);

		button.addActionListener(action);

		return button;
	}

	private class SaveFileDialogAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser chooser = new JFileChooser();

			int selection = chooser.showSaveDialog(null);

			if (selection == JFileChooser.APPROVE_OPTION)
			{
				output.setText("Saving to: " + chooser.getSelectedFile());
			}
		}
	}

	private class OpenFileDialogAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser chooser = new JFileChooser();

			int selection = chooser.showOpenDialog(null);

			if (selection == JFileChooser.APPROVE_OPTION)
			{
				output.setText("Opening file: " + chooser.getSelectedFile());
			}
		}
	}

	private class ColorDialogAction implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			Color c = JColorChooser.showDialog(null, "Choose", Color.BLUE);

			if (c != null)
			{
				output.setText("Color selected: " + c);
			}
		}
	}

	public static void main(String[] args)
	        throws UnsupportedLookAndFeelException
	{
		new DialogTest().display();
	}
}
