package com.example.managementtool.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.managementtool.R

class SignUpActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up2)
        setSupportActionBar()

    }

    private fun setSupportActionBar(){
        val toolbar =findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_sign_up_activity)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        if (supportActionBar != null){
            supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_black_color_back_24dp)
        }
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        findViewById<Button>(R.id.btn_sign_up).setOnClickListener {
            registerUser()
        }
    }

     private fun registerUser(){
         val name:String = findViewById<TextView>(R.id.et_name).toString().trim{it <= ' '}
         val email:String = findViewById<TextView>(R.id.et_email).toString().trim{it <= ' '}
         val password:String = findViewById<TextView>(R.id.et_password).toString().trim{it <= ' '}

          if(validateForm(name, email, password)){
              Toast.makeText(this,"now you can register",Toast.LENGTH_SHORT).show()
          }
     }

     private fun validateForm(name:String,email:String,password:String):Boolean{
          return when{
              TextUtils.isEmpty(name) ->{
                  showErrorSnackBar("Please enter a name")
                  false
              }
              TextUtils.isEmpty(email) ->{
                  showErrorSnackBar("Please enter a email")
                  false
              }
              TextUtils.isEmpty(password) ->{
                  showErrorSnackBar("Please enter a password")
                  false
              }else ->{
                  true
              }
          }

     }

}