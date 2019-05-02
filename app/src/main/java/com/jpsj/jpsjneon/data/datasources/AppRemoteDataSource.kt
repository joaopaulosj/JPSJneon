package com.jpsj.jpsjneon.data.datasources

import com.jpsj.jpsjneon.base.Constants
import com.jpsj.jpsjneon.data.models.TransferResponse
import com.jpsj.jpsjneon.data.services.AppService
import com.jpsj.jpsjneon.utils.extensions.createService
import io.reactivex.Single

interface AppRemoteDataSource {
	fun getTransfers(): Single<List<TransferResponse>>
	fun sendMoney(contactId: Long, amount: Double): Single<Any>
}

class AppRemoteDataSourceImpl : AppRemoteDataSource {
	
	private val service = createService(AppService::class.java, Constants.BASE_URL)
	
	override fun getTransfers() = service.getTransfers()
	
	override fun sendMoney(contactId: Long, amount: Double) = service.sendMoney(contactId, amount)
}