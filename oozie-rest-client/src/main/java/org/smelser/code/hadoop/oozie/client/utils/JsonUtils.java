package org.smelser.code.hadoop.oozie.client.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class JsonUtils {

    /**
     * Format a Date in RFC822 GMT.
     *
     * @param date date to format.
     * @return RFC822 GMT for the date, <code>null</code> if the date was <code>null</null>.
     */
    public static String formatDateRfc822(Date date) {
        if (date != null) {
            SimpleDateFormat dateFormater = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            dateFormater.setTimeZone(TimeZone.getTimeZone("GMT"));
            return dateFormater.format(date);
        }
        return null;
    }

    /**
     * Parse a string in RFC822 GMT format.
     *
     * @param str string to parse.
     * @return parsed date, <code>null</code> if the string was <code>null</null> or in an invalid format.
     */
    public static Date parseDateRfc822(String str) {
        if (str != null) {
            try {
                SimpleDateFormat dateFormater = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
                dateFormater.setTimeZone(TimeZone.getTimeZone("GMT"));
                return dateFormater.parse(str);
            }
            catch (ParseException ex) {
                return null;
            }
        }
        return null;
    }

}
