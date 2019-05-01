package com.jpsj.jpsjneon.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jpsj.jpsjneon.R
import com.jpsj.jpsjneon.history.createHistoryIntent
import com.jpsj.jpsjneon.utils.extensions.loadCircleImage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.partial_home_avatar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMockUser()
        setListeners()
    }

    private fun setMockUser() {
        avatarIv.loadCircleImage(getString(R.string.home_user_picture))
        homeUserNameTv.text = getString(R.string.home_user_name)
        homeUserEmailTv.text = getString(R.string.home_user_email)
    }

    private fun setListeners() {
        homeHistoryBtn.setOnClickListener { startActivity(createHistoryIntent()) }
    }

}