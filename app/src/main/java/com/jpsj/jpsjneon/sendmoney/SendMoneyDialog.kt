package com.jpsj.jpsjneon.sendmoney

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatDialog
import com.jpsj.jpsjneon.R
import com.jpsj.jpsjneon.base.AppInjector
import com.jpsj.jpsjneon.data.models.ContactModel
import com.jpsj.jpsjneon.utils.CurrencyMask
import com.jpsj.jpsjneon.utils.extensions.loadCircleImage
import com.jpsj.jpsjneon.utils.extensions.singleSubscribe
import kotlinx.android.synthetic.main.dialog_send_money.*
import kotlinx.android.synthetic.main.partial_avatar.view.*
import org.jetbrains.anko.longToast
import java.util.*

class SendMoneyDialog(context: Context, private val contact: ContactModel) : AppCompatDialog(context) {

    private val repository = AppInjector.getAppRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.requestFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.dialog_send_money)
        window?.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        setListeners()
        displayContact()
    }

    private fun displayContact() {
        dialogSendMoneyNameTv.text = contact.name
        dialogSendMoneyPhoneTv.text = contact.phone
        dialogSendMoneyIv.avatarIv.loadCircleImage(contact.picture)
        dialogSendMoneyEt.setText("0")
    }

    private fun setListeners() {
        dialogSendMoneyBtn.setOnClickListener { sendMoney() }
        dialogSendMoneyEt.addTextChangedListener(
            CurrencyMask.insert(Locale("pt", "BR"), dialogSendMoneyEt, true)
        )
    }

    private fun sendMoney() {
        val amount = CurrencyMask.parseValue(dialogSendMoneyEt.text.toString())
        if (amount > 0) {
            dialogSendMoneyBtn.setLoading(true)
            repository.sendMoney(contact.id, amount)
                .singleSubscribe(onSuccess = {
                    dialogSendMoneyBtn.setLoading(false)
                    context.longToast(R.string.send_money_succeeded)
                    dismiss()
                }, onError = {
                    dialogSendMoneyBtn.setLoading(false)
                    context.longToast(it.message ?: context.getString(R.string.placeholder_error))
                })
        } else {
            context.longToast(context.getString(R.string.dialog_send_money_empty_value))
        }
    }


}