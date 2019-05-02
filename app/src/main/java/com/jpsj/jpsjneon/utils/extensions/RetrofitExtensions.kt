package com.jpsj.jpsjneon.utils.extensions

import com.jpsj.jpsjneon.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun <S> createService(serviceClass: Class<S>, url: String): S {

	val httpClient = OkHttpClient.Builder().apply {
		if (BuildConfig.DEBUG) {
			addInterceptor(HttpLoggingInterceptor().apply {
				level = HttpLoggingInterceptor.Level.BODY
			})
		}
	}

	val retrofit = Retrofit.Builder()
		.baseUrl(url)
		.client(httpClient.build())
		.addConverterFactory(GsonConverterFactory.create())
		.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
	
	return retrofit.build().create(serviceClass)
}