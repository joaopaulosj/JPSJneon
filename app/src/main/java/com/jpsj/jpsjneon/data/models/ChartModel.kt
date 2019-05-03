package com.jpsj.jpsjneon.data.models

import com.jpsj.jpsjneon.utils.extensions.formatToCurrency
import com.jpsj.jpsjneon.utils.extensions.getInitialsLetters

class ChartModel(
    val amount: Double,
    val clientId: Long,
    val clientName: String,
    val clientPic: String?
) {

    val initials: String?
        get() = clientName.getInitialsLetters()

    val amountDisplay: String
        get() = amount.formatToCurrency(false)

    //Percentages are not from 0 to 100 so it has a margin on the top and the bottom of the chart
    fun totalPercent(chartMax: Double): Float {
        return (15 + (amount / chartMax * 70)).toFloat()
    }

    fun chartPercent(chartMax: Double): Float {
        return 1 - totalPercent(chartMax) / 100f
    }
}