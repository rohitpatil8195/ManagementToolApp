package com.example.managementtool.activities

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.managementtool.R
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


open class BaseActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false

    private lateinit var mProgressDialog:Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

    }

    fun showProgressDialog(text:String){
        mProgressDialog = Dialog(this)

        mProgressDialog.setContentView(R.layout.dialog_progress)
         
    
        mProgressDialog.findViewById<TextView>(R.id.tv_progress_text).text =text

        mProgressDialog.show()

    }

    fun hideProgressDialog(){
        mProgressDialog.dismiss()
    }

     fun getCurrantUserID():String{
         return FirebaseAuth.getInstance().currentUser!!.uid
     }


     fun doubleBackToExit(){
         if(doubleBackToExitPressedOnce){
             super.onBackPressed()
             return
         }
         this.doubleBackToExitPressedOnce = true
         Toast.makeText(this,resources.getString(R.string.please_click_back_again_to_exit),Toast.LENGTH_SHORT).show()

         Handler(Looper.getMainLooper()).postDelayed({
             doubleBackToExitPressedOnce =false
         },2000)
     }


     fun showErrorSnackBar(massage:String){
          val snackBar = Snackbar.make(findViewById(android.R.id.content),
              massage,Snackbar.LENGTH_LONG)
         val snackbarView =snackBar.view
         snackbarView.setBackgroundColor(ContextCompat.getColor(this,R.color.snackbar_error_color))
         snackBar.show()
     }

}