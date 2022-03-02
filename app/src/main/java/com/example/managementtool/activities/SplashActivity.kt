package com.example.managementtool.activities

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import android.os.Handler
import android.os.Looper
import com.example.managementtool.R


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_splash)

        val typeFace:Typeface = Typeface.createFromAsset(assets,"FindHappines.otf")
         val tv_app_name = findViewById<TextView>(R.id.tv_app_name)
        tv_app_name.typeface =typeFace

        Handler(Looper.getMainLooper()).postDelayed({
             startActivity(Intent(this, IntroActivity::class.java))
            finish()
        },2500)
    }
}