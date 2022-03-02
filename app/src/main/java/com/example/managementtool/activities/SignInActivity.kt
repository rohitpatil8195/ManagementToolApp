package com.example.managementtool.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.managementtool.R

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        setSupportActionBar()
    }

    private fun setSupportActionBar(){
        val toolbar =findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_sign_in_activity)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        if (supportActionBar != null){
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}