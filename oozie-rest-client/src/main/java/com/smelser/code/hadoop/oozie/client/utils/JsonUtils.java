package com.smelser.code.hadoop.oozie.client.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class JsonUtils {

    int hi = 1;
    StopWatch stopWatch = new StopWatch();

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

    @Retryable(value = {Exception.class}, backoff = @Backoff(delay = 1000), maxAttempts = 10)
    public void run(){
        if(hi%5 != 0){
            hi++;
            stopWatch.start("task " + hi);
            try {
                stopWatch.stop();
                throw new EmptyStackException();
            }finally {
                System.out.println(String.format("first attempt %s %s -- %s", stopWatch.getLastTaskInfo().getTaskName(), stopWatch.getLastTaskInfo().getTimeMillis(), new Date()));
            }
        }
    }

}
