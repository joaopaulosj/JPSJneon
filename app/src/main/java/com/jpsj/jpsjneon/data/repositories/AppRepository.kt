package com.jpsj.jpsjneon.data.repositories

import com.jpsj.jpsjneon.data.datasources.AppRemoteDataSource
import com.jpsj.jpsjneon.data.models.ContactModel
import com.jpsj.jpsjneon.data.models.TransferModel
import com.jpsj.jpsjneon.data.models.toContactModel
import com.jpsj.jpsjneon.data.models.toTransferModel
import io.reactivex.Single

interface AppRepository {
    fun getTransfers(): Single<List<TransferModel>>
    fun getContacts(): Single<List<ContactModel>>
    fun sendMoney(contactId: Long, amount: Double): Single<Any>
}

class AppRepositoryImpl(val datasource: AppRemoteDataSource) : AppRepository {

    override fun getTransfers() = datasource.getTransfers().map { response ->
        response.map { it.toTransferModel() }
    }

    override fun getContacts() = datasource.getTransfers().map { response ->
        response.asSequence().map { it.toContactModel() }.distinctBy { it.id }.sortedBy { it.name }.toList()
    }
    
    override fun sendMoney(contactId: Long, amount: Double) = datasource.sendMoney(contactId, amount)
}