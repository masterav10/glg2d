package org.jogamp.glg2d.newt;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This class demonstrates isShowing() through a card layout.
 * 
 * @author Dan Avila
 * 
 */
public class CardLayoutTest extends NEWTUITest
{
	private CardLayout cardLayout;

	@Override
	protected JComponent getContentPane()
	{
		JPanel contentPane = new JPanel(new BorderLayout());

		JLabel results = new JLabel();

		JPanel swapPanel = createSwapPanel();
		JPanel buttons = new JPanel(new GridLayout(1, 0));

		buttons.add(createColorButton("RED", swapPanel, results));
		buttons.add(createColorButton("BLUE", swapPanel, results));

		contentPane.add(results, BorderLayout.NORTH);
		contentPane.add(swapPanel, BorderLayout.CENTER);
		contentPane.add(buttons, BorderLayout.SOUTH);

		return contentPane;
	}

	private JPanel createSwapPanel()
	{
		cardLayout = new CardLayout();
		JPanel panel = new JPanel(cardLayout);

		JPanel background = new JPanel();
		background.setName("RED");
		background.setBackground(Color.RED);
		panel.add(background, "RED");

		background = new JPanel();
		background.setName("BLUE");
		background.setBackground(Color.BLUE);
		panel.add(background, "BLUE");

		return panel;
	}

	private JButton createColorButton(final String name,
	        final Container parent, final JLabel output)
	{
		JButton button = new JButton(name);

		button.setText(name);

		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				cardLayout.show(parent, name);

				String text = "";

				for (Component comp : parent.getComponents())
				{
					text += comp.getName() + " showing=" + comp.isShowing();
					text += "     ";
				}

				output.setText(text);
			}
		});

		return button;
	}

	public static void main(String[] args)
	        throws UnsupportedLookAndFeelException
	{
		new CardLayoutTest().display();
	}
}
