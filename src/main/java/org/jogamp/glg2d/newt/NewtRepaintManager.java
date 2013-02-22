package org.jogamp.glg2d.newt;

import javax.swing.JComponent;
import javax.swing.RepaintManager;

public class NewtRepaintManager extends RepaintManager
{
	private static final NewtRepaintManager INST = new NewtRepaintManager();

	public static RepaintManager get()
	{
		return INST;
	}

	@Override
	public void addDirtyRegion(JComponent c, int x, int y, int w, int h)
	{
		super.addDirtyRegion(c, x, y, w, h);
	}

	@Override
	public void paintDirtyRegions()
	{
		super.paintDirtyRegions();
	}
}
