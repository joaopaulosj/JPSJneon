package com.jpsj.jpsjneon.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jpsj.jpsjneon.R
import com.jpsj.jpsjneon.data.models.ChartModel
import com.jpsj.jpsjneon.utils.extensions.animateFromZero
import com.jpsj.jpsjneon.utils.extensions.formatToDecimal
import com.jpsj.jpsjneon.utils.extensions.loadCircleImage
import com.jpsj.jpsjneon.utils.extensions.setVisible
import kotlinx.android.synthetic.main.item_chart.view.*
import kotlinx.android.synthetic.main.partial_avatar.view.*

class HistoryChartAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<ChartModel> = emptyList()
    private var greatestAmount: Double = 1.0
    private val animatedItems = mutableListOf<Int>()

    fun setItems(items: List<ChartModel>, greatestAmount: Double) {
        this.items = items
        this.greatestAmount = greatestAmount
        animatedItems.clear()
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
                itemChartAmountTv.text = item.amount.formatToDecimal()
                avatarIv.setVisible(item.clientPic != null, true)
                avatarIv.loadCircleImage(item.clientPic)
                avatarTv.setVisible(item.clientPic == null)
                avatarTv.text = item.initials

                val percent = item.chartPercent(greatestAmount)

                if (animatedItems.contains(adapterPosition)) {
                    itemChartGdl.setGuidelinePercent(1 - percent) //this 1 is because of the constraint layout orientation
                } else {
                    //Animate the chart bar from zero to the amount percent
                    percent.animateFromZero {
                        itemChartGdl.setGuidelinePercent(1 - it) //this 1 is because of the constraint layout orientation

                        if ((it * 100).toInt() == percent.toInt())
                            animatedItems.add(adapterPosition) //set as animated to not repeat animation
                    }
                }
            }
        }
    }
}