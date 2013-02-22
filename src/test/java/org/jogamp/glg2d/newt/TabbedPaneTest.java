package org.jogamp.glg2d.newt;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Contains all of the provides tests into one big test case.
 * 
 * @author Naval Undersea Warfare Center, Newport RI
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

		return pane;
	}

	public static void main(String[] args)
	        throws UnsupportedLookAndFeelException
	{
		new TabbedPaneTest().display(600, 500);
	}
}
