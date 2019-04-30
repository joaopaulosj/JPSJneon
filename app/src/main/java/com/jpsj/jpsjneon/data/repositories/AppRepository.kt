package com.jpsj.jpsjneon.data.repositories

import com.jpsj.jpsjneon.data.datasources.AppRemoteDataSource
import com.jpsj.jpsjneon.data.models.TransferModel
import com.jpsj.jpsjneon.data.models.toModel
import io.reactivex.Single

interface AppRepository {
	fun getTransfers(): Single<List<TransferModel>>
}

class AppRepositoryImpl(val datasource: AppRemoteDataSource) : AppRepository {
	
	override fun getTransfers() = datasource.getTransfers().map {response ->
		response.map { it.toModel() }
	}
}