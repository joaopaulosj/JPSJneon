package com.jpsj.jpsjneon.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jpsj.jpsjneon.R
import com.jpsj.jpsjneon.data.models.ChartModel
import com.jpsj.jpsjneon.helpers.loadCircleImage
import kotlinx.android.synthetic.main.item_chart.view.*
import kotlinx.android.synthetic.main.partial_avatar.view.*

class HistoryChartAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
	
	private var items: List<ChartModel> = emptyList()
	private var greatestAmount: Double = 1.0
	
	fun setItems(items: List<ChartModel>, greatestAmount: Double) {
		this.items = items
		this.greatestAmount = greatestAmount
		notifyDataSetChanged()
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_chart, parent, false)
		return ItemViewHolder(itemView)
	}
	
	override fun getItemCount(): Int = items.size
	
	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		if (holder is ItemViewHolder) {
			holder.bind(items[position])
		}
	}
	
	inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(item: ChartModel) {
			itemView.apply {
				item.clientPic?.let {
					itemChartAvatarIv.avatarIv.loadCircleImage(it)
				} ?: run {
					itemChartAvatarIv.avatarIv.loadCircleImage(null)
				}
				itemChartAmountTv.text = item.amount.toInt().toString()
				
				val percent = (0.1 + (item.amount / greatestAmount.toFloat() * 0.8))
				itemChartGdl.setGuidelinePercent(1 - percent.toFloat())
			}
		}
	}
}