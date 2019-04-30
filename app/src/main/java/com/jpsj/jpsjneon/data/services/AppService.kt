package com.jpsj.jpsjneon.data.services

import com.jpsj.jpsjneon.data.models.TransferResponse
import io.reactivex.Single
import retrofit2.http.GET

interface AppService {
	
	@GET("GetTransfers")
	fun getTransfers(): Single<List<TransferResponse>>
	
}