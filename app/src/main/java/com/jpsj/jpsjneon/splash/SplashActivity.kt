package com.jpsj.jpsjneon.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.jpsj.jpsjneon.home.createHomeIntent

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            startActivity(createHomeIntent())
            finish()
        }, 1000)
    }

}