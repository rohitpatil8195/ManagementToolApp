package com.example.managementtool.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.managementtool.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignInActivity : BaseActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        auth = FirebaseAuth.getInstance()
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

        findViewById<Button>(R.id.btn_sign_in).setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser(){
        val email:String = findViewById<EditText>(R.id.et_signInEmail).text.toString().trim{it <= ' '}
        val password:String = findViewById<EditText>(R.id.et_SignInPassword).text.toString().trim{it <= ' '}


        if(validateForm(email, password)){
            showProgressDialog(resources.getString(R.string.Please_wait))

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    hideProgressDialog()
                    if (task.isSuccessful) {
                        val firebaseUser: FirebaseUser = task.result!!.user!!
                        val user = firebaseUser.email
                        val intent=Intent(this,MainActivity::class.java)
                         startActivity(intent)
                        Toast.makeText(
                            this,
                            "you have successfully LoggedIn email $user",
                            Toast.LENGTH_LONG
                        ).show()

                    } else {
                        // If sign in fails, display a message to the user. ${task.exception
                        Toast.makeText(baseContext, "Authentication failed.}",
                            Toast.LENGTH_SHORT).show()
                    }
                }

        }
    }


    private fun validateForm(email:String,password:String):Boolean{

        return when{
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