package com.jpsj.jpsjneon.data.models

import com.jpsj.jpsjneon.base.Constants
import com.jpsj.jpsjneon.extensions.toCalendar
import java.util.*

class ChartModel(
	val amount: Double,
	val clientId: Long,
	val clientName: String,
	val clientPic: String?
)