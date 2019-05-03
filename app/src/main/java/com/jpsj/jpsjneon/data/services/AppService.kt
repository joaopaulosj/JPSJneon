package com.jpsj.jpsjneon.data.services

import com.jpsj.jpsjneon.data.models.TransferResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface AppService {

    @GET("GetTransfers")
    fun getTransfers(): Single<List<TransferResponse>>

    @FormUrlEncoded
    @POST("SendMoney")
    fun sendMoney(
        @Field("clientId") contactId: Long,
        @Field("amount") amount: Double
    ): Single<Any>

}