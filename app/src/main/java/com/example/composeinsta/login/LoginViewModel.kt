package com.example.composeinsta.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _user = MutableLiveData<String>()
    val user: LiveData<String> = _user

    fun onEmailChanged(email: String) {
        _email.value = email
    }

    fun onUserChanged(user: String) {
        _user.value = user
    }

}