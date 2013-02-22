package org.jogamp.glg2d.newt;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Contains all of the provides tests into one big test case.
 * 
 * @author Dan Avila
 * 
 */
public class TabbedPaneTest extends NEWTUITest
{
	private List<NEWTUITest> tests = new ArrayList<>();

	@Override
	protected JComponent getContentPane()
	{
		tests.add(new ButtonTest());
		tests.add(new CardLayoutTest());
		tests.add(new ColorChooserTest());
		tests.add(new ComboBoxTest());
		tests.add(new DialogTest());
		tests.add(new InternalFrameTest());
		tests.add(new LayeredPaneTest());
		tests.add(new ListTest());
		tests.add(new ProgressBarTest());
		tests.add(new SliderTest());
		tests.add(new SpinnerTest());
		tests.add(new TableTest());
		tests.add(new TextTest());
		tests.add(new TreeTest());

		JTabbedPane pane = new JTabbedPane();

		for (NEWTUITest test : tests)
		{
			pane.addTab(test.getName(), test.getContentPane());
		}

		JPanel panel = new JPanel(new GridLayout(2, 2));

		panel.add(pane);
		panel.add(exit());
		panel.add(exit());
		panel.add(new CanvasTest().getContentPane());

		return panel;
	}

	private JButton exit()
	{
		JButton button = new JButton("Exit");

		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});

		return button;
	}

	public static void main(String[] args)
	        throws UnsupportedLookAndFeelException
	{
		new TabbedPaneTest().setSize(600, 500).display();
	}
}
