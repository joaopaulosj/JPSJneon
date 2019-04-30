package com.jpsj.jpsjneon.base

import androidx.appcompat.app.AppCompatActivity
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import com.jpsj.jpsjneon.R
import org.jetbrains.anko.toast

abstract class BaseActivity : AppCompatActivity() {
	
	protected fun setToolbar(@StringRes title: Int, displayBackButton: Boolean = true) {
		supportActionBar?.setDisplayHomeAsUpEnabled(displayBackButton)
		try {
			val toolbar = findViewById<Toolbar>(R.id.toolbar)
			setSupportActionBar(toolbar)
			setTitle(title)
		} catch (ex: Exception) {
			ex.printStackTrace()
		}
	}
	
	protected fun displayError(message: String?) {
		toast(message ?: "Erro desconhecido")
	}
	
}
