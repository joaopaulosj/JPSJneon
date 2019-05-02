package com.jpsj.jpsjneon.data.models

import com.jpsj.jpsjneon.utils.extensions.getInitialsLetters

class ContactModel(
    val id: Long,
    val name: String,
    val phone: String?,
    val picture: String?
) {

    val initials: String?
        get() = name.getInitialsLetters()

}

fun TransferResponse.toContactModel(): ContactModel {
    return ContactModel(
        Cliente.Id, Cliente.Nome, Cliente.Celular, Cliente.FotoUrl
    )
}