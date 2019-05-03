package com.jpsj.jpsjneon.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jpsj.jpsjneon.R
import com.jpsj.jpsjneon.ui.history.createHistoryIntent
import com.jpsj.jpsjneon.ui.sendmoney.createSendMoneyIntent
import com.jpsj.jpsjneon.utils.extensions.loadCircleImage
import com.jpsj.jpsjneon.utils.extensions.startActivitySlideTransition
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.partial_home_avatar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayMockUser()
        setListeners()
    }

    private fun displayMockUser() {
        avatarIv.loadCircleImage(getString(R.string.home_user_picture))
        homeUserNameTv.text = getString(R.string.home_user_name)
        homeUserEmailTv.text = getString(R.string.home_user_email)
    }

    private fun setListeners() {
        homeHistoryBtn.setOnClickListener { startActivitySlideTransition(createHistoryIntent()) }
        homeSendMoneyBtn.setOnClickListener { startActivitySlideTransition(createSendMoneyIntent()) }
    }

}