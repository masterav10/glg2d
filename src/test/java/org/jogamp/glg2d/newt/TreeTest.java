package org.jogamp.glg2d.newt;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * This test demonstrates a JTree in a JScroolPane.
 * 
 * @author Naval Undersea Warfare Center, Newport RI
 * 
 */
public class TreeTest extends NEWTUITest
{
	@Override
	protected JComponent getContentPane()
	{
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Root");

		DefaultMutableTreeNode colors = add(top, "Colors");
		add(colors, "Red");
		add(colors, "Green");
		add(colors, "Blue");

		DefaultMutableTreeNode books = add(top, "Food");
		add(books, "Eggs");
		add(books, "Chicken");

		DefaultMutableTreeNode trees = add(top, "Trees");
		add(trees, "Pine");
		add(trees, "Willow");

		DefaultMutableTreeNode numbers = add(top, "Numbers");

		for (int i = 0; i < 1000; i++)
		{
			add(numbers, Integer.toString(i));
		}

		return new JScrollPane(new JTree(top));
	}

	private DefaultMutableTreeNode add(DefaultMutableTreeNode parent,
	        String name)
	{
		DefaultMutableTreeNode node = new DefaultMutableTreeNode("Name");

		parent.add(node);

		return node;
	}

	public static void main(String[] args)
	        throws UnsupportedLookAndFeelException
	{
		new TreeTest().display();
	}
}
