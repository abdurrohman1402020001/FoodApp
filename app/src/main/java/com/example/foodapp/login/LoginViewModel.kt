package com.example.foodapp.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class LoginViewModel : ViewModel() {
    private val _login = MutableLiveData<String>()
    val login: LiveData<String> = _login

    private val _user = MutableLiveData<FirebaseUser?>()
    val user: LiveData<FirebaseUser?> = _user

    fun loginFirebase(email: String, password: String) =
        Firebase.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                _login.postValue("Login Success!")
            } else {
                _login.postValue(it.exception.toString())
            }
        }

}