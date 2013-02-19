package org.jogamp.glg2d.newt;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Demonstrates a layered pane performing its job.
 * 
 * @author Naval Undersea Warfare Center, Newport RI
 */
public class LayeredPaneTest extends NEWTUITest
{
	private int x = 0;
	private int y = 0;

	@Override
	protected JComponent getContentPane()
	{
		JLayeredPane pane = new JLayeredPane();

		pane.add(create(Color.BLUE), JLayeredPane.FRAME_CONTENT_LAYER);
		pane.add(create(Color.RED), JLayeredPane.DEFAULT_LAYER);
		pane.add(create(Color.GREEN), JLayeredPane.PALETTE_LAYER);
		pane.add(create(Color.ORANGE), JLayeredPane.MODAL_LAYER);
		pane.add(create(Color.YELLOW), JLayeredPane.POPUP_LAYER);
		pane.add(create(Color.MAGENTA), JLayeredPane.DRAG_LAYER);

		return pane;
	}

	private JPanel create(Color color)
	{
		JPanel panel = new JPanel();
		panel.setOpaque(true);
		panel.setBackground(color);
		panel.setBounds(x, y, 200, 200);

		x += 30;
		y += 30;

		return panel;
	}

	public static void main(String[] args)
	        throws UnsupportedLookAndFeelException
	{
		new LayeredPaneTest().display();
	}
}
