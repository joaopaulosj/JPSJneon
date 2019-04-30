package com.jpsj.jpsjneon.data.models

import com.jpsj.jpsjneon.base.Constants
import com.jpsj.jpsjneon.extensions.toCalendar
import java.util.*

class TransferModel(
	val id: Long,
	val price: Double,
	val date: Calendar,
	val clientId: Long,
	val clientName: String,
	val clientPhone: String?,
	val clientPic: String?
)

fun TransferResponse.toModel(): TransferModel {
	return TransferModel(
		Id, Valor, Data.toCalendar(Constants.SERVER_DATE_FORMAT),
		Cliente.Id, Cliente.Nome, Cliente.Celular, Cliente.FotoUrl
	)
}