package com.jpsj.jpsjneon.utils.extensions

import android.view.View
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

fun View.setVisible(visible: Boolean, useInvisible: Boolean = false) {
	visibility = when {
		visible -> View.VISIBLE
		useInvisible -> View.INVISIBLE
		else -> View.GONE
	}
}

fun RecyclerView.setup(
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

fun SwipeRefreshLayout.setColorsFromProject() {
	setColorSchemeColors(ContextCompat.getColor(this.context, R.color.colorPrimary),
			ContextCompat.getColor(this.context, R.color.colorAccent))
}

fun snackBar(coordinator: CoordinatorLayout, message: String, retryText: String,
          action: (v: View) -> Unit?, indefinite: Boolean = true): Snackbar {
	return Snackbar.make(coordinator, message, if (indefinite) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG)
			.setAction(retryText) { v -> action(v) }
}