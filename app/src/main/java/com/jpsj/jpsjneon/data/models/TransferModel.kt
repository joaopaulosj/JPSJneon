package com.jpsj.jpsjneon.data.models

import com.jpsj.jpsjneon.base.Constants
import com.jpsj.jpsjneon.utils.extensions.formatToCurrency
import com.jpsj.jpsjneon.utils.extensions.getInitialsLetters
import com.jpsj.jpsjneon.utils.extensions.toCalendar
import java.util.*

class TransferModel(
    val id: Long,
    val amount: Double,
    val date: Calendar,
    val clientId: Long,
    val clientName: String,
    val clientPhone: String?,
    val clientPic: String?
) {

    val initials: String?
        get() = clientName.getInitialsLetters()

    val amountDisplay: String
        get() = amount.formatToCurrency()

}

fun TransferResponse.toTransferModel(): TransferModel {
    return TransferModel(
        Id, Valor, Data.toCalendar(Constants.SERVER_DATE_FORMAT),
        Cliente.Id, Cliente.Nome, Cliente.Celular, Cliente.FotoUrl
    )
}