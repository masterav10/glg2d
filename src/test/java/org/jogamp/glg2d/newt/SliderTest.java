package org.jogamp.glg2d.newt;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderTest extends NEWTUITest
{

	@Override
	protected JComponent getContentPane()
	{
		JPanel panel = new JPanel(new BorderLayout());

		JPanel canvas = new JPanel();

		panel.add(create(JSlider.HORIZONTAL, canvas), BorderLayout.SOUTH);
		panel.add(create(JSlider.VERTICAL, canvas), BorderLayout.WEST);

		panel.add(canvas, BorderLayout.CENTER);

		return panel;
	}

	private JSlider create(int orientiation, final JPanel target)
	{
		JSlider color = new JSlider(orientiation, 0, 255, 122);

		color.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting())
				{
					int color = (int) source.getValue();

					target.setBackground(new Color(color, 0, 0));
				}
			}
		});

		// Turn on labels at major tick marks.
		color.setMajorTickSpacing(10);
		color.setMinorTickSpacing(1);
		color.setPaintTicks(true);
		color.setPaintLabels(true);

		return color;
	}

	public static void main(String[] args)
	        throws UnsupportedLookAndFeelException
	{
		new SliderTest().setSize(700, 400).display();
	}
}
