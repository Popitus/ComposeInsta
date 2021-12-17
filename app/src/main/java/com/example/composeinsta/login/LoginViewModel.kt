package com.example.composeinsta.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {

    companion object {
        const val MIN_PASSWORD_LENGHT = 6
    }

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _openDialog = MutableLiveData<Boolean>()
    val openDialog: LiveData<Boolean> = _openDialog

    private val _enable = MutableLiveData<Boolean>()
    val enable: LiveData<Boolean> = _enable

    fun onOpenDialogChanged(openDialog:Boolean) {
        _openDialog.value = openDialog
    }


    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _enable.value = isValidEmail(email) && isValidPassword(password)
    }

    private fun isValidEmail(email:String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isValidPassword(password: String): Boolean {
        return (password.length >= MIN_PASSWORD_LENGHT)
    }



}