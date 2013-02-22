package org.jogamp.glg2d.newt;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * This test combines JTables and Split panes.
 * 
 * @author Dan Avila
 * 
 */
public class TableTest extends NEWTUITest
{
	@Override
	protected JComponent getContentPane()
	{
		JPanel panel = new JPanel(new GridLayout(1, 2));

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);

		JTable table = create();
		splitPane.setLeftComponent(new JScrollPane(table));

		table = create();
		splitPane.setRightComponent(new JScrollPane(table));

		panel.add(splitPane);

		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerLocation(150);

		table = create();
		splitPane.setBottomComponent(new JScrollPane(table));

		table = create();
		splitPane.setTopComponent(new JScrollPane(table));

		panel.add(splitPane);

		return panel;
	}

	private JTable create()
	{
		int size = 10;

		String[] columns = new String[size];
		Object[][] data = new Object[size][size];

		for (int i = 0; i < size; i++)
		{
			columns[i] = "";

			for (int x = 0; x < size; x++)
			{
				data[i][x] = x * i + x;
			}
		}

		JTable table = new JTable(data, columns);

		return table;
	}

	public static void main(String[] args)
	        throws UnsupportedLookAndFeelException
	{
		new TableTest().display(600, 300);
	}
}
