package com.jpsj.jpsjneon.base

import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.jpsj.jpsjneon.R
import org.jetbrains.anko.toast

abstract class BaseActivity : AppCompatActivity() {
	
	protected fun setToolbar(@StringRes title: Int, displayBackButton: Boolean = true) {
		try {
			val toolbar = findViewById<Toolbar>(R.id.toolbar)
			setSupportActionBar(toolbar)
			supportActionBar?.setDisplayHomeAsUpEnabled(displayBackButton)
			setTitle(title)
		} catch (ex: Exception) {
			ex.printStackTrace()
		}
	}
	
	protected fun displayErrorToast(message: String?) {
		toast(message ?: "Erro desconhecido")
	}
	
}
