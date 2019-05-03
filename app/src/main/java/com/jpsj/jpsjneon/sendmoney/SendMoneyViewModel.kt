package com.jpsj.jpsjneon.sendmoney

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jpsj.jpsjneon.data.models.ContactModel
import com.jpsj.jpsjneon.data.repositories.AppRepository
import com.jpsj.jpsjneon.utils.SingleLiveEvent
import com.jpsj.jpsjneon.utils.extensions.singleSubscribe

class SendMoneyViewModel(private val repository: AppRepository) : ViewModel() {

    val contactsList = MutableLiveData<List<ContactModel>>()
    val errorEvents = SingleLiveEvent<String?>()
    val isLoading = SingleLiveEvent<Boolean>()

    fun loadContacts() {
        isLoading.value = true
        repository.getContacts()
            .singleSubscribe(onSuccess = {
                isLoading.value = false
                contactsList.value = it
            }, onError = {
                errorEvents.value = it.message
                isLoading.value = false
            })
    }
}

class SendMoneyViewModelFactory(val repository: AppRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SendMoneyViewModel(repository) as T
    }
}