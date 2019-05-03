package com.jpsj.jpsjneon.data.models

class TransferResponse(
    val Id: Long,
    val Valor: Double,
    val Token: String,
    val Data: String,
    val Cliente: ClientResponse
)

class ClientResponse(
    val Id: Long,
    val Celular: String?,
    val Nome: String,
    val FotoUrl: String?
)