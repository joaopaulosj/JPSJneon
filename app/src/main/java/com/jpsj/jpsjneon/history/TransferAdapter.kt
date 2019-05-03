package com.jpsj.jpsjneon.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jpsj.jpsjneon.R
import com.jpsj.jpsjneon.data.models.TransferModel
import com.jpsj.jpsjneon.utils.extensions.loadCircleImage
import com.jpsj.jpsjneon.utils.extensions.setVisible
import kotlinx.android.synthetic.main.item_transfer.view.*
import kotlinx.android.synthetic.main.partial_avatar.view.*

class TransferAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<TransferModel> = emptyList()

    fun setItems(items: List<TransferModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_transfer, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(items[position])
        }
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: TransferModel) {
            itemView.apply {
                itemTransferNameTv.text = item.clientName
                itemTransferPhoneTv.text = item.clientPhone
                itemTransferAmountTv.text = item.amountDisplay
                avatarIv.setVisible(item.clientPic != null, true)
                avatarIv.loadCircleImage(item.clientPic)
                avatarTv.setVisible(item.clientPic == null)
                avatarTv.text = item.initials
            }
        }
    }
}