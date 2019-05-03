package com.jpsj.jpsjneon.utils.extensions

import android.animation.ValueAnimator
import java.text.NumberFormat
import java.util.*

fun Float.animateFromZero(duration: Long = 1500, callback: (value: Float) -> Unit) {
    val valueAnimator = ValueAnimator.ofInt(0, this.toInt())
    valueAnimator.duration = duration
    valueAnimator.addUpdateListener {
        callback.invoke((it.animatedValue as Int) / 100f)
    }
    valueAnimator.start()
}

fun Double.formatToCurrency(showCurrency: Boolean = true, locale: Locale = Locale("pt", "BR")): String {
    val currencyFormatter = NumberFormat.getCurrencyInstance(locale)

    val str = currencyFormatter.format(this)
    return if (showCurrency)
        str
    else
        str.replace(Regex("-?[^\\d,.]"), "")

}