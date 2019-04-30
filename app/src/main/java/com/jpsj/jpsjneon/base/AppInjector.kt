package com.jpsj.jpsjneon.base

import com.jpsj.jpsjneon.data.datasources.AppRemoteDataSource
import com.jpsj.jpsjneon.data.datasources.AppRemoteDataSourceImpl
import com.jpsj.jpsjneon.data.repositories.AppRepository
import com.jpsj.jpsjneon.data.repositories.AppRepositoryImpl
import com.jpsj.jpsjneon.history.HistoryViewModelFactory

object AppInjector {
	
	//ViewModels
	fun getHistoryViewModelFactory(): HistoryViewModelFactory = HistoryViewModelFactory(getAppRepository())
	
	//Repositories
	private fun getAppRepository(): AppRepository = AppRepositoryImpl(getAppDatasource())
	
	//Data sources
	private fun getAppDatasource(): AppRemoteDataSource = AppRemoteDataSourceImpl()
	
}