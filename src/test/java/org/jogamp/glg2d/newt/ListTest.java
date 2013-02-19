package org.jogamp.glg2d.newt;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Demonstrates a list, both within and outside a scrollpane.
 * 
 * @author Naval Undersea Warfare Center, Newport RI
 * 
 */
public class ListTest extends NEWTUITest
{
	private static final String[] DATA = new String[]
	{ "AXE", "BUFFALO", "CARD", "DEER", "ENERGY", "FIELD", "GRAPHICS", "HOUSE",
	        "IGLOO", "JAM", "KILT", "LOOSE", "MAN", "NEAT", "OPENGL",
	        "PROFOUND", "QUOTE", "RASCAL", "STREET", "TOUGH", "UNDER",
	        "VERTEX", "WILD", "X", "YOUNG", "Z-ORDER" };

	@Override
	protected JComponent getContentPane()
	{
		Box box = Box.createVerticalBox();

		JList<String> simple = new JList<>(DATA);
		simple.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		box.add(new JScrollPane(simple));

		JList<String> data = new JList<>(DATA);
		data.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		data.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		data.setVisibleRowCount(5);
		box.add(data);

		return box;
	}

	public static void main(String[] args)
	        throws UnsupportedLookAndFeelException
	{
		new ListTest().display();
	}
}
