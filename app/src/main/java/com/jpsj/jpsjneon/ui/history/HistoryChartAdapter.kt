package com.jpsj.jpsjneon.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jpsj.jpsjneon.R
import com.jpsj.jpsjneon.data.models.ChartModel
import com.jpsj.jpsjneon.utils.extensions.animateFromZero
import com.jpsj.jpsjneon.utils.extensions.loadCircleImage
import com.jpsj.jpsjneon.utils.extensions.setVisible
import kotlinx.android.synthetic.main.item_chart.view.*
import kotlinx.android.synthetic.main.partial_avatar.view.*

class HistoryChartAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<ChartModel> = emptyList()
    private var greatestAmount: Double = 1.0
    private var animated = false

    fun setItems(items: List<ChartModel>, greatestAmount: Double) {
        this.items = items
        this.greatestAmount = greatestAmount
        animated = false
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
                itemChartAmountTv.text = item.amountDisplay
                avatarIv.setVisible(item.clientPic != null)
                avatarIv.loadCircleImage(item.clientPic)
                avatarTv.setVisible(item.clientPic == null)
                avatarTv.text = item.initials


                if (animated) {
                    itemChartGdl.setGuidelinePercent(item.chartPercent(greatestAmount))
                } else {
                    val percent = item.totalPercent(greatestAmount)
                    //Animate the chart bar from zero to the amount percent
                    percent.animateFromZero {
                        itemChartGdl.setGuidelinePercent(1 - it) //this 1 is because of the constraint layout orientation

                        if ((it * 100).toInt() == percent.toInt())
                            animated = true //set as animated to not repeat animation when recycled
                    }
                }
            }
        }
    }
}