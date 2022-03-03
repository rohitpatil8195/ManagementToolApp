package com.example.managementtool.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.managementtool.R
import com.example.managementtool.firebase.FireStoreClass
import com.example.managementtool.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

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
         val name:String = findViewById<EditText>(R.id.et_name).text.toString().trim{it <= ' '}
         val email:String = findViewById<EditText>(R.id.et_email).text.toString().trim{it <= ' '}
         val password:String = findViewById<EditText>(R.id.et_password).text.toString().trim{it <= ' '}



          if(validateForm(name, email, password)){
             showProgressDialog(resources.getString(R.string.Please_wait))
              FirebaseAuth.getInstance()
                  .createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->

                      if (task.isSuccessful) {
                          val firebaseUser: FirebaseUser = task.result!!.user!!
                          val registerEmail = firebaseUser.email!!
                          val user = User(firebaseUser.uid,name,registerEmail)
                         FireStoreClass().registerUser(this,user)

                      } else {
                          Toast.makeText(this, task.exception!!.message, Toast.LENGTH_SHORT).show()
                      }
                  }

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

    fun userRegisteredSuccess() {
        Toast.makeText(
            this,
            "you have successfully register the email",
            Toast.LENGTH_LONG
        ).show()
        hideProgressDialog()
        FirebaseAuth.getInstance().signOut()
        finish()
    }

}