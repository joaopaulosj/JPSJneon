package com.jpsj.jpsjneon.history

import android.content.Context
import android.os.Bundle
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jpsj.jpsjneon.R
import com.jpsj.jpsjneon.base.AppInjector
import com.jpsj.jpsjneon.base.BaseActivity
import com.jpsj.jpsjneon.data.models.ChartModel
import com.jpsj.jpsjneon.data.models.TransferModel
import com.jpsj.jpsjneon.utils.extensions.setColorsFromProject
import com.jpsj.jpsjneon.utils.extensions.setup
import kotlinx.android.synthetic.main.activity_history.*
import org.jetbrains.anko.intentFor

class HistoryActivity : BaseActivity() {
	
	private val viewModel by lazy {
		val factory = AppInjector.getHistoryViewModelFactory()
		ViewModelProviders.of(this, factory).get(HistoryViewModel::class.java)
	}
	
	private val chartAdapter: HistoryChartAdapter by lazy {
		val adapter = HistoryChartAdapter()
		historyChartRv.setup(adapter, layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))
		ViewCompat.setNestedScrollingEnabled(historyChartRv, false)
		adapter
	}
	
	private val transfersAdapter: TransferAdapter by lazy {
		val adapter = TransferAdapter()
		historyTransfersRv.setup(adapter)
		adapter
	}
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_history)
		
		setToolbar(R.string.history_title)
		historySwipeRefresh.setColorsFromProject()
		
		setListeners()
		setObservers()
		
		viewModel.loadTransfers()
	}
	
	private fun setListeners() {
		historySwipeRefresh.setOnRefreshListener { viewModel.loadTransfers() }
	}
	
	private fun setObservers() {
		viewModel.apply {
			transfersList.observe(this@HistoryActivity, Observer { displayTransfers(it) })
			chartList.observe(this@HistoryActivity, Observer { displayChart(it.first, it.second) })
			errorEvents.observe(this@HistoryActivity, Observer { displayError(it) })
			isLoading.observe(this@HistoryActivity, Observer { displayloading(it) })
		}
	}
	
	private fun displayloading(isLoading: Boolean) {
		historySwipeRefresh.isRefreshing = isLoading
	}
	
	private fun displayTransfers(transfers: List<TransferModel>) {
		transfersAdapter.setItems(transfers)
	}
	
	private fun displayChart(items: List<ChartModel>, greatestAmount: Double) {
		chartAdapter.setItems(items, greatestAmount)
	}
}

fun Context.createHistoryIntent() = intentFor<HistoryActivity>()