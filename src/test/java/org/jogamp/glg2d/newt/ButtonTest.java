package org.jogamp.glg2d.newt;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This test demonstrates various buttons.
 * 
 * @author Dan Avila
 * 
 */
public class ButtonTest extends NEWTUITest
{
	@Override
	protected JComponent getContentPane()
	{
		int gridSize = 6;

		JLabel selection = new JLabel("Clicks will show up here.");
		JPanel contentPane = new JPanel(new BorderLayout());

		JPanel buttonPanel = new JPanel(new GridLayout(gridSize, gridSize));

		for (int i = 0; i < gridSize * gridSize; i++)
		{
			AbstractButton button = createRandomButtonType(i);
			button.setToolTipText("Hi! I am " + button.getText());
			button.addActionListener(new PrintIfVisibleAction(selection));

			buttonPanel.add(button);
		}

		contentPane.add(selection, BorderLayout.NORTH);
		contentPane.add(buttonPanel, BorderLayout.CENTER);

		return contentPane;
	}

	public static void main(String[] args)
	        throws UnsupportedLookAndFeelException
	{
		new ButtonTest().display();
	}

	private AbstractButton createRandomButtonType(int i)
	{
		int value = (int) (Math.random() * 4);

		switch (value)
		{
		case 0:
			return new JToggleButton("T" + i);
		case 1:
			return new JCheckBox("C" + i);
		case 2:
			return new JRadioButton("R" + i);
		default:
			return new JButton("B" + i);
		}
	}

	private static class PrintIfVisibleAction implements ActionListener
	{
		private JLabel label;

		public PrintIfVisibleAction(JLabel selection)
		{
			this.label = selection;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			AbstractButton button = (AbstractButton) e.getSource();

			if (button.isShowing())
			{
				String name = button.getClass().getSimpleName() + " "
				        + button.getText();

				label.setText(name + " clicked!");
			}
		}
	}
}
