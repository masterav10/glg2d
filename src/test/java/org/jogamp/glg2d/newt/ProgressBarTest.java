package org.jogamp.glg2d.newt;

import java.awt.GridLayout;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UnsupportedLookAndFeelException;

public class ProgressBarTest extends NEWTUITest
{
	@Override
	protected JComponent getContentPane()
	{
		JPanel panel = new JPanel(new GridLayout(0, 1));

		final JProgressBar progress = new JProgressBar();

		JProgressBar indeterminate = new JProgressBar();
		indeterminate.setIndeterminate(true);

		Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(
		        new Runnable()
		        {
			        @Override
			        public void run()
			        {
				        int i = (progress.getValue() % 100) + 1;

				        progress.setValue(i);
				        progress.setMaximum(100);

				        progress.setString(Integer.toString(i) + "%");
				        progress.setStringPainted(true);
			        }
		        }, 0, 100, TimeUnit.MILLISECONDS);

		panel.add(progress);
		panel.add(indeterminate);

		return panel;
	}

	public static void main(String[] args)
	        throws UnsupportedLookAndFeelException
	{
		new ProgressBarTest().display();
	}
}
