package com.jpsj.jpsjneon.helpers

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jpsj.jpsjneon.R

fun ImageView.loadCircleImage(url: String?, addPlaceholder: Boolean = false): Boolean {
	try {
		val optionsToApply = RequestOptions()
		if (addPlaceholder)
			optionsToApply.placeholder(ContextCompat.getDrawable(context, R.drawable.ic_placeholder_user))
		
		optionsToApply.circleCrop()
		Glide.with(context).load(url).apply(optionsToApply).into(this)
	} catch (e: Exception) {
		e.printStackTrace()
		return false
	}
	
	return true
}

fun RecyclerView.config(
	adapter: RecyclerView.Adapter<in RecyclerView.ViewHolder>,
	layoutManager: RecyclerView.LayoutManager? = LinearLayoutManager(this.context),
	decoration: RecyclerView.ItemDecoration? = null,
	hasFixedSize: Boolean = true
) {
	
	this.adapter = adapter
	this.layoutManager = layoutManager
	this.setHasFixedSize(hasFixedSize)
	decoration?.let { this.addItemDecoration(it) }
}