package com.jpsj.jpsjneon.data.datasources

import com.jpsj.jpsjneon.base.Constants
import com.jpsj.jpsjneon.data.models.TransferResponse
import com.jpsj.jpsjneon.data.services.AppService
import com.jpsj.jpsjneon.utils.extensions.createService
import io.reactivex.Single

interface AppRemoteDataSource {
	fun getTransfers(): Single<List<TransferResponse>>
}

class AppRemoteDataSourceImpl : AppRemoteDataSource {
	
	val service = createService(AppService::class.java, Constants.BASE_URL)
	
	override fun getTransfers() = service.getTransfers()
}