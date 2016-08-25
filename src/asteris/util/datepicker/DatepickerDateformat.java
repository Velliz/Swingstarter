package asteris.util.datepicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JFormattedTextField.AbstractFormatter;

public class DatepickerDateformat extends AbstractFormatter {

    private String datePattern = "dd MMMM yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern, new Locale("id", "ID"));
    private Date predefinedDate = null;

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            if (predefinedDate != null) return dateFormatter.format(predefinedDate);
            else return dateFormatter.format(cal.getTime());
        }

        return "";
    }

    public DatepickerDateformat() {
    }

    public DatepickerDateformat(Date date) {
        predefinedDate = date;
    }

}
