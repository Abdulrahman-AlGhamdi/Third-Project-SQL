package com.kotlin.thirdprojectsql.ui.login

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    fun isPhoneValid(phone: String): Boolean = when {
        phone.isEmpty() -> false
        phone.length != 10 -> false
        else -> true
    }
}