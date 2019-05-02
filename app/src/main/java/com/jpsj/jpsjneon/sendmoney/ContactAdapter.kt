package com.jpsj.jpsjneon.sendmoney

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jpsj.jpsjneon.R
import com.jpsj.jpsjneon.data.models.ContactModel
import com.jpsj.jpsjneon.utils.extensions.loadCircleImage
import com.jpsj.jpsjneon.utils.extensions.setVisible
import kotlinx.android.synthetic.main.item_contact.view.*
import kotlinx.android.synthetic.main.partial_avatar.view.*

class ContactAdapter(private val onClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
	
	private var items: List<ContactModel> = emptyList()
	
	fun setItems(items: List<ContactModel>) {
		this.items = items
		notifyDataSetChanged()
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
		return ItemViewHolder(itemView)
	}
	
	override fun getItemCount(): Int = items.size
	
	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		if (holder is ItemViewHolder) {
			holder.bind(items[position])
		}
	}
	
	interface OnItemClickListener {
		fun onItemClicked(id: ContactModel)
	}
	
	inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(item: ContactModel) {
			itemView.apply {
				itemContactNameTv.text = item.name
				itemContactPhoneTv.text = item.phone
				avatarIv.setVisible(item.picture != null, true)
				avatarIv.loadCircleImage(item.picture)
				avatarTv.setVisible(item.picture == null)
				avatarTv.text = item.initials
				setOnClickListener { onClickListener.onItemClicked(item) }
			}
		}
	}
}