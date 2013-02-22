package org.jogamp.glg2d.newt;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This test demonstrates text components and scroll panes.
 * 
 * @author Dan Avila
 * 
 */
public class TextTest extends NEWTUITest
{
	@Override
	protected JComponent getContentPane()
	{
		JPanel panel = new JPanel(new GridLayout(3, 2));

		JTextPane pane = new JTextPane();
		pane.setText("");

		String text = "Edit Me\n";

		for (int i = 0; i < 5; i++)
		{
			text += text;
		}

		JTextArea area = new JTextArea();
		area.setText(text);

		JTextField field = new JTextField();
		field.setText("Edit Me");

		JPasswordField pass = new JPasswordField();
		pass.setText("Password");

		// TODO: Cycle HTML files through this editor.
		JEditorPane editor = new JEditorPane();

		// TODO: make this do some formatting.
		JFormattedTextField format = new JFormattedTextField();

		panel.add(new JScrollPane(editor));
		panel.add(new JScrollPane(pane));
		panel.add(new JScrollPane(area));
		panel.add(field);
		panel.add(pass);
		panel.add(format);

		return panel;
	}

	public static void main(String[] args)
	        throws UnsupportedLookAndFeelException
	{
		new TextTest().display();
	}
}
