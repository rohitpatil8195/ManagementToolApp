package com.example.managementtool.firebase

import android.util.Log
import com.example.managementtool.activities.SignUpActivity
import com.example.managementtool.models.User
import com.example.managementtool.utils.constants
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FireStoreClass {
     private val mFireStore =FirebaseFirestore.getInstance()

      fun registerUser(activity:SignUpActivity,userInfo: User){
          mFireStore.collection(constants.USERS)
              .document(getCurrantUserId()).set(userInfo, SetOptions.merge())
              .addOnSuccessListener {
                  activity.userRegisteredSuccess()
              }.addOnFailureListener {
                  Log.e(activity.javaClass.simpleName,"Error")
              }

      }

    fun getCurrantUserId():String{
        return FirebaseAuth.getInstance().currentUser!!.uid
    }
}