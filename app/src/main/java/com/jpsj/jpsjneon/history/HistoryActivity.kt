package com.jpsj.jpsjneon.history

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jpsj.jpsjneon.R
import com.jpsj.jpsjneon.base.AppInjector
import com.jpsj.jpsjneon.base.BaseActivity
import com.jpsj.jpsjneon.data.models.ChartModel
import com.jpsj.jpsjneon.data.models.TransferModel
import com.jpsj.jpsjneon.helpers.config
import kotlinx.android.synthetic.main.activity_history.*
import org.jetbrains.anko.intentFor

class HistoryActivity : BaseActivity() {
	
	private val viewModel by lazy {
		val factory = AppInjector.getHistoryViewModelFactory()
		ViewModelProviders.of(this, factory).get(HistoryViewModel::class.java)
	}
	
	private val chartAdapter: HistoryChartAdapter by lazy {
		val adapter = HistoryChartAdapter()
		historyChartRv.config(adapter, layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))
		adapter
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_history)
		
		setToolbar(R.string.history_title)
		
		setObservers()
		
		viewModel.loadTransfers()
	}
	
	private fun setObservers() {
		viewModel.apply {
			transfersList.observe(this@HistoryActivity, Observer { displayTransfers(it) })
			chartList.observe(this@HistoryActivity, Observer { displayChart(it.first, it.second) })
			errorEvents.observe(this@HistoryActivity, Observer { displayError(it) })
		}
	}
	
	private fun displayTransfers(transfers: List<TransferModel>) {
	
	}
	
	private fun displayChart(items: List<ChartModel>, greatestAmount: Double) {
		chartAdapter.setItems(items, greatestAmount)
	}
}

fun Context.createHistoryIntent() = intentFor<HistoryActivity>()