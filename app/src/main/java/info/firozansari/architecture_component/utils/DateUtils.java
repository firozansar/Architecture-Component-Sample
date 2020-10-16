package info.firozansari.architecture_component.utils;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by Firoz Ansari on 16/10/2020.
 */
public class DateUtils {
    public static final String JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String JSON_DATE_FORMAT_2 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public static Date parseAPIDate(String dateStr) {
        try {
            return getJsonFormatter(JSON_DATE_FORMAT_2).parse(dateStr);
        } catch (ParseException e) {
            try {
                return getJsonFormatter(JSON_DATE_FORMAT).parse(dateStr);
            } catch (ParseException e2) {
                return null;
            }
        }
    }

    public static SimpleDateFormat getJsonFormatter(String jsonDateFormat) {
        SimpleDateFormat simpleDateFormat;
        switch (jsonDateFormat) {
            case DateUtils.JSON_DATE_FORMAT_2:
                simpleDateFormat = createSimpleJsonDateFormat2();
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("+0000"));
                break;
            default:
                simpleDateFormat = new SimpleDateFormat(jsonDateFormat, Locale.UK);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("+0000"));
        }
        return simpleDateFormat;
    }

    private static SimpleDateFormat createSimpleJsonDateFormat2() {
        return new SimpleDateFormat(DateUtils.JSON_DATE_FORMAT_2, Locale.UK) {
            public Date parse(@NonNull String source, @NonNull ParsePosition pos) { // Android doesn't support +01:00 format
                return super.parse(source.replaceFirst(":(?=[0-9]{2}$)", ""), pos);
            }
        };
    }

    public static String covertDateToText(String dateStr) {
        String convTime = "";
        String suffix = "Ago";

        Date nowTime = new Date();
        Date sourceDate = parseAPIDate(dateStr);
        if(sourceDate != null) {

            long dateDiff = nowTime.getTime() - sourceDate.getTime();

            long second = TimeUnit.MILLISECONDS.toSeconds(dateDiff);
            long minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff);
            long hour = TimeUnit.MILLISECONDS.toHours(dateDiff);
            long day = TimeUnit.MILLISECONDS.toDays(dateDiff);

            if (second < 60) {
                convTime = second + " Seconds " + suffix;
            } else if (minute < 60) {
                convTime = minute + " Minutes " + suffix;
            } else if (hour < 24) {
                convTime = hour + " Hours " + suffix;
            } else if (day >= 7) {
                if (day > 360) {
                    convTime = (day / 360) + " Years " + suffix;
                } else if (day > 30) {
                    convTime = (day / 30) + " Months " + suffix;
                } else {
                    convTime = (day / 7) + " Week " + suffix;
                }
            } else if (day < 7) {
                convTime = day + " Days " + suffix;
            }
        }

        return convTime;
    }

}


