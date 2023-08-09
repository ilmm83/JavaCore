package edu.custom_tags;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;


public class WeekdayTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private static final String[] WD = { "", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
	private String date;

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public int doEndTag() throws JspException {
		var calendar = new GregorianCalendar();
		var formater = new SimpleDateFormat("yyyy-MM-dd");
		formater.setLenient(true);

		if (date != null && date.length() > 0) {
			var d = new Date();

			try {
				d = formater.parse(date);
			} catch (Exception e) {
				throw new JspException("Date parsing failed: " + e.getMessage());
			}
			
			calendar.setTime(d);
		}

		try {
			pageContext.getOut().print(WD[calendar.get(Calendar.DAY_OF_WEEK)]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return EVAL_PAGE;
	}
}
