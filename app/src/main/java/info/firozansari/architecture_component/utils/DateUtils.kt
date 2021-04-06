package info.firozansari.architecture_component.utils

import java.text.ParseException
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

/**
 * Created by Firoz Ansari on 16/10/2020.
 */
object DateUtils {
    const val JSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val JSON_DATE_FORMAT_2 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

    fun parseAPIDate(dateStr: String?): Date? {
        return try {
            getJsonFormatter(JSON_DATE_FORMAT_2).parse(dateStr)
        } catch (e: ParseException) {
            try {
                getJsonFormatter(JSON_DATE_FORMAT).parse(dateStr)
            } catch (e2: ParseException) {
                null
            }
        }
    }

    fun getJsonFormatter(jsonDateFormat: String?): SimpleDateFormat {
        val simpleDateFormat: SimpleDateFormat
        when (jsonDateFormat) {
            JSON_DATE_FORMAT_2 -> {
                simpleDateFormat = createSimpleJsonDateFormat2()
                simpleDateFormat.timeZone = TimeZone.getTimeZone("+0000")
            }
            else -> {
                simpleDateFormat = SimpleDateFormat(jsonDateFormat, Locale.UK)
                simpleDateFormat.timeZone = TimeZone.getTimeZone("+0000")
            }
        }
        return simpleDateFormat
    }

    private fun createSimpleJsonDateFormat2(): SimpleDateFormat {
        return object : SimpleDateFormat(JSON_DATE_FORMAT_2, Locale.UK) {
            override fun parse(
                source: String,
                pos: ParsePosition
            ): Date { // Android doesn't support +01:00 format
                return super.parse(source.replaceFirst(":(?=[0-9]{2}$)".toRegex(), ""), pos)
            }
        }
    }

    @JvmStatic
    fun covertDateToText(dateStr: String?): String {
        var convTime = ""
        val suffix = "Ago"
        val nowTime = Date()
        val sourceDate = parseAPIDate(dateStr)
        if (sourceDate != null) {
            val dateDiff = nowTime.time - sourceDate.time
            val second = TimeUnit.MILLISECONDS.toSeconds(dateDiff)
            val minute = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
            val hour = TimeUnit.MILLISECONDS.toHours(dateDiff)
            val day = TimeUnit.MILLISECONDS.toDays(dateDiff)
            convTime = if (second < 60) {
                "$second Seconds $suffix"
            } else if (minute < 60) {
                "$minute Minutes $suffix"
            } else if (hour < 24) {
                "$hour Hours $suffix"
            } else if (day >= 7) {
                if (day > 360) {
                    (day / 360).toString() + " Years " + suffix
                } else if (day > 30) {
                    (day / 30).toString() + " Months " + suffix
                } else {
                    (day / 7).toString() + " Week " + suffix
                }
            } else {
                "$day Days $suffix"
            }
        }
        return convTime
    }
}