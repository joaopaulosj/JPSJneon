package com.jpsj.jpsjneon.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.NumberFormat
import java.util.*

object CurrencyMask {
	
	fun unmask(string: String): String {
		return string.replace("[R$�,.()]".toRegex(), "")
	}
	
	fun parseValue(string: String): Double {
		return unmask(string).toDouble() / 100
	}
	
	fun insert(locale: Locale, editText: EditText, displayCurrency: Boolean): TextWatcher {
		return object : TextWatcher {
			var isUpdating: Boolean = false
			var old = ""
			
			override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int,
			                           count: Int) {
				if (isUpdating) {
					isUpdating = false
					return
				}
				
				val string = charSequence.toString()
				
				if (string != old) {
					isUpdating = true
					val cleanString = unmask(string)
					
					try {
						val parsed = cleanString.toDouble()
						var formated = NumberFormat.getCurrencyInstance(locale).format(parsed / 100)
						
						if (!displayCurrency)
							formated = formated.replace("R$", "R$ ")
						
						old = formated
						editText.setText(formated)
						editText.setSelection(editText.text.length)
					} catch (e: NumberFormatException) {
						e.printStackTrace()
					}
				}
				
				// is erasing text
				if (old.length > string.length && string.isNotEmpty()) {
					old = string
					return
				}
			}
			
			override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
			override fun afterTextChanged(s: Editable) {}
		}
	}
}