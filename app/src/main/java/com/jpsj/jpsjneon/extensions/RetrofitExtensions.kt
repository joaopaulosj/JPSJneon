package com.jpsj.jpsjneon.extensions

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun <S> createService(serviceClass: Class<S>, url: String): S {
	val retrofit = Retrofit.Builder()
		.baseUrl(url)
		.addConverterFactory(GsonConverterFactory.create())
		.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
	
	return retrofit.build().create(serviceClass)
}