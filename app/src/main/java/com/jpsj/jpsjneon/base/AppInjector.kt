package com.jpsj.jpsjneon.base

import com.jpsj.jpsjneon.data.datasources.AppRemoteDataSource
import com.jpsj.jpsjneon.data.datasources.AppRemoteDataSourceImpl
import com.jpsj.jpsjneon.data.repositories.AppRepository
import com.jpsj.jpsjneon.data.repositories.AppRepositoryImpl
import com.jpsj.jpsjneon.ui.history.HistoryViewModelFactory
import com.jpsj.jpsjneon.ui.sendmoney.SendMoneyViewModelFactory

object AppInjector {
	
	//ViewModels
	fun getHistoryViewModelFactory(): HistoryViewModelFactory =
		HistoryViewModelFactory(getAppRepository())
	fun getSendMoneyViewModelFactory(): SendMoneyViewModelFactory =
		SendMoneyViewModelFactory(getAppRepository())

	//Repositories
	fun getAppRepository(): AppRepository = AppRepositoryImpl(getAppDatasource())
	
	//Data sources
	private fun getAppDatasource(): AppRemoteDataSource = AppRemoteDataSourceImpl()
	
}