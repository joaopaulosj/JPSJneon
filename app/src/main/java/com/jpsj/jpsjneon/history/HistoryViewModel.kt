package com.jpsj.jpsjneon.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jpsj.jpsjneon.data.models.ChartModel
import com.jpsj.jpsjneon.data.models.TransferModel
import com.jpsj.jpsjneon.data.repositories.AppRepository
import com.jpsj.jpsjneon.utils.SingleLiveEvent
import com.jpsj.jpsjneon.utils.extensions.singleSubscribe

class HistoryViewModel(private val repository: AppRepository) : ViewModel() {
	
	val transfersList = MutableLiveData<List<TransferModel>>()
	val chartList = MutableLiveData<Pair<List<ChartModel>, Double>>()
	val errorEvents = SingleLiveEvent<String?>()
	val isLoading = SingleLiveEvent<Boolean>()
	
	fun loadTransfers() {
		isLoading.value = true
		repository.getTransfers()
				.singleSubscribe(onSuccess = {
					loadChart(it)
				}, onError = {
					errorEvents.value = it.message
					isLoading.value = false
				})
	}
	
	private fun loadChart(transfers: List<TransferModel>) {
		transfersList.value = transfers
		val groups = transfers.groupBy { it.clientId }
		val chartItems = groups.map {
			ChartModel(
					amount = it.value.sumByDouble { it.amount },
					clientId = it.key, clientName = it.value.first().clientName,
					clientPic = it.value.first().clientPic
			)
		}
		
		val greatestAmount = chartItems.maxBy { it.amount }
		chartList.value = Pair(chartItems.sortedByDescending { it.amount },
				greatestAmount?.amount ?: 1.0)
		isLoading.value = false
	}
	
}

class HistoryViewModelFactory(val repository: AppRepository) : ViewModelProvider.NewInstanceFactory() {
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		return HistoryViewModel(repository) as T
	}
}