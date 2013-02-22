package org.jogamp.glg2d.newt;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * This test demonstrates a basic spinner that holds a date.
 * 
 * @author Dan Avila
 * 
 */
public class SpinnerTest extends NEWTUITest
{
	@Override
	protected JComponent getContentPane()
	{
		JPanel panel = new JPanel();

		Calendar calendar = new GregorianCalendar();
		SpinnerDateModel model = new SpinnerDateModel();

		Date initDate = calendar.getTime();
		calendar.add(Calendar.YEAR, -100);
		Date earliestDate = calendar.getTime();
		calendar.add(Calendar.YEAR, 200);
		Date latestDate = calendar.getTime();
		model = new SpinnerDateModel(initDate, earliestDate, latestDate,
		        Calendar.YEAR);

		JSpinner spinner = new JSpinner(model);

		spinner.setEditor(new JSpinner.DateEditor(spinner, "MM/yyyy"));

		panel.add(spinner);

		return panel;
	}

	public static void main(String[] args)
	        throws UnsupportedLookAndFeelException
	{
		new SpinnerTest().setSize(100, 100).display();
	}
}
