package com.example.managementtool.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.managementtool.R

class IntroActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val btn_signUp= findViewById<Button>(R.id.btnSignUp)
        btn_signUp.setOnClickListener {
             val intent=Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        val btn_signIn= findViewById<Button>(R.id.btnSignIn)
        btn_signIn.setOnClickListener {
            val intent=Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}