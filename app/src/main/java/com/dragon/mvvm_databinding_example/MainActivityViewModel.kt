package com.dragon.mvvm_databinding_example

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class MainActivityViewModel : ViewModel(){

    private var auth: FirebaseAuth

    val userInfo : MutableLiveData<UserInfo> by lazy {
        MutableLiveData<UserInfo>()
    }

    val status : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    init {
        userInfo.value = UserInfo()
        auth = FirebaseAuth.getInstance()
    }

    fun onSignIn(){
        Log.d("Something",userInfo.value.toString())
        auth.signInWithEmailAndPassword(userInfo.value?.username ?: "", userInfo.value?.password ?: "")
                .addOnCompleteListener {task ->
                    if(task.isSuccessful) {
                        Log.d("Firebase", "signInWithEmail:success")
                        status.value = true
                    } else {
                        Log.d("Firebase", "signInWithEmail:fail")
                        status.value = false
                    }
                }
    }

    fun onSignOut() {

    }

}
