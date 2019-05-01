package com.jpsj.jpsjneon.utils.extensions

import android.animation.ValueAnimator
import java.text.DecimalFormat
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

fun Double.formatToCurrency(locale: Locale = Locale("pt", "BR")): String {
	val currencyFormatter = NumberFormat.getCurrencyInstance(locale)
	return currencyFormatter.format(this)
}

fun Double.formatToDecimal(): String {
	val df = DecimalFormat()
	df.minimumFractionDigits = 2
	return df.format(this)
}