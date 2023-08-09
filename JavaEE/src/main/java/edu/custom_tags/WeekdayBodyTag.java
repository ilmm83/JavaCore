package edu.custom_tags;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.BodyTagSupport;


public class WeekdayBodyTag extends BodyTagSupport {

	private static final long serialVersionUID = 1L;
	private static final String[] WD = { "", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
	private boolean bodyless = true;

	@Override
	public int doAfterBody() throws JspException {
		var date = getBodyContent().getString();

		if (date.length() > 0) {
			var d = new Date();
			var calendar = new GregorianCalendar();
			var formater = new SimpleDateFormat("yyyy-MM-dd");
			formater.setLenient(true);

			try {
				d = formater.parse(date);
			} catch (Exception e) {
				e.printStackTrace();
			}

			calendar.setTime(d);

			try {
				getPreviousOut().print(WD[calendar.get(Calendar.DAY_OF_WEEK)]);
			} catch (Exception e) {
				e.printStackTrace();
				throw new JspException("Weekday writing failed: " + e.getMessage());
			}

			bodyless = false;
		}

		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {

		if (bodyless) {
			var calendar = new GregorianCalendar();

			try {
				pageContext.getOut().print(WD[calendar.get(Calendar.DAY_OF_WEEK)]);
			} catch (Exception e) {
				e.printStackTrace();
				throw new JspException("Weekday writing failed: " + e.getMessage());
			}
		}

		return EVAL_PAGE;
	}
}
