package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String{
    val dateFormat = SimpleDateFormat(pattern, Locale("RU") )
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits=TimeUnits.SECOND): Date{
    var time =this.time

    time +=when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE-> value * MINUTE
        TimeUnits.HOUR-> value * HOUR
        TimeUnits.DAY-> value * DAY

    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date : Date = Date()): String {
   var time = this.time
   var dlt:Long = date.time - time
   var r: String

   r = when (dlt){
       in 0L * SECOND..1L * SECOND -> "только что"
       in 1L * SECOND..45L * SECOND -> "несколько секунд назад"
       in 46L * SECOND..75 * SECOND ->  "минуту назад"
       in 75L * SECOND..45L * MINUTE -> "${dlt / MINUTE} минут назад"
       in 45L * MINUTE..75L * MINUTE  -> "час назад"
       in 75L * MINUTE..22L* HOUR ->  "${dlt / HOUR} часа назад"
       in 22L * HOUR..26L * HOUR -> "день назад"
       in 26L * HOUR..360 * DAY -> "${dlt / DAY} дней назад"
       in 360 * DAY..36000 * DAY ->  "более года назад"

       in -0L * SECOND downTo -1L * SECOND -> "только что"
       in -1L * SECOND downTo -45L * SECOND -> "через несколько секунд"
       in -46L * SECOND downTo -75L * SECOND ->  "через минуту"

       in -75L * SECOND downTo -45L * MINUTE -> "через ${dlt / MINUTE*-1} минут "
       in -45L * MINUTE downTo -75L * MINUTE  -> "через час"
       in -75L * MINUTE downTo -22L* HOUR -> "через ${dlt / HOUR*-1} часов"
       in -22L * HOUR downTo -26L * HOUR -> "через день"
       in -26L * HOUR downTo -360L * DAY -> "через ${dlt / DAY*-1} дней"
       in -360 * DAY downTo -36000L * DAY ->  "более чем через год"
       else -> "Хрен знает когда"
   }

    return r

}



enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY
}