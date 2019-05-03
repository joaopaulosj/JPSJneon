package com.jpsj.jpsjneon.ui.sendmoney

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jpsj.jpsjneon.R
import com.jpsj.jpsjneon.base.AppInjector
import com.jpsj.jpsjneon.base.BaseActivity
import com.jpsj.jpsjneon.data.models.ContactModel
import com.jpsj.jpsjneon.utils.extensions.setColorsFromProject
import com.jpsj.jpsjneon.utils.extensions.setup
import com.jpsj.jpsjneon.utils.extensions.snackBar
import kotlinx.android.synthetic.main.activity_send_money.*
import org.jetbrains.anko.intentFor

class SendMoneyActivity : BaseActivity() {

    private val viewModel by lazy {
        val factory = AppInjector.getSendMoneyViewModelFactory()
        ViewModelProviders.of(this, factory).get(SendMoneyViewModel::class.java)
    }

    private val contactsAdapter by lazy {
        val adapter = ContactAdapter(object :
            ContactAdapter.OnItemClickListener {
            override fun onItemClicked(contact: ContactModel) {
                openContactDialog(contact)
            }
        })
        sendMoneyRv.setup(adapter)
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_money)

        setToolbar(R.string.send_money_title)
        sendMoneySwipeRefresh.setColorsFromProject()

        setListeners()
        setObservers()

        viewModel.loadContacts()
    }

    private fun setListeners() {
        sendMoneySwipeRefresh.setOnRefreshListener { viewModel.loadContacts() }
    }

    private fun setObservers() {
        viewModel.apply {
            contactsList.observe(this@SendMoneyActivity, Observer { displayContact(it) })
            errorEvents.observe(this@SendMoneyActivity, Observer { displayError(it) })
            isLoading.observe(this@SendMoneyActivity, Observer { displayloading(it) })
        }
    }

    private fun openContactDialog(contact: ContactModel) {
        SendMoneyDialog(this, contact).show()
    }

    private fun displayContact(contacts: List<ContactModel>) {
        contactsAdapter.setItems(contacts)
    }

    private fun displayloading(isLoading: Boolean) {
        sendMoneySwipeRefresh.isRefreshing = isLoading
    }

    private fun displayError(message: String?) {
        snackBar(
            coordinator, message ?: getString(R.string.placeholder_error),
            getString(R.string.try_again), true
        ) {
            viewModel.loadContacts()
        }.show()
    }
}

fun Context.createSendMoneyIntent() = intentFor<SendMoneyActivity>()