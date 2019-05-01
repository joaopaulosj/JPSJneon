package com.jpsj.jpsjneon.utils

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.jpsj.jpsjneon.R
import com.jpsj.jpsjneon.utils.extensions.setVisible
import kotlinx.android.synthetic.main.loading_button.view.*

class LoadingButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) :
    FrameLayout(context, attrs, defStyle) {

    init {
        LayoutInflater.from(context).inflate(R.layout.loading_button, this, true)
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.LoadingButton, 0, 0)

            val typedValue = TypedValue()
            context.theme.resolveAttribute(R.attr.selectableItemBackground, typedValue, true)
            ContextCompat.getDrawable(context, typedValue.resourceId)

            //TEXT
            loadingButtonTextView.text = typedArray.getString(R.styleable.LoadingButton_btnText)
            typedArray.recycle()
        }
    }

    fun setLoading(loading: Boolean) {
        isEnabled = !loading
        loadingButtonProgressBar.setVisible(loading)
        loadingButtonTextView.setVisible(!loading, true)
    }

}