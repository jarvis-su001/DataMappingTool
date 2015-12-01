package com.jarvis.tools;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by C5023792 on 12/1/2015.
 */
public class DateTools {

    public static Date[] getDaysOfGivenMonth(Date month) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(month);
        int min = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date[] allDays = new Date[max];
        for (int i = min - 1; i < max; i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i + 1);
            allDays[i] = calendar.getTime();
        }
        return allDays;
    }

}
