package com.example.mychat.presentation.screen.auth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.parse.ParseUser

class AuthViewModal : ViewModel() {
    private var _myUserName: MutableState<String> = mutableStateOf("")
    val myUserName: State<String> = _myUserName

    private var _myPassword: MutableState<String> = mutableStateOf("")
    val myPassword: State<String> = _myPassword

    fun setUsername(text: String) {
        _myUserName.value = text
    }

    fun setPassword(text: String) {
        _myPassword.value = text
    }

    fun loginTheUser(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        ParseUser.logInInBackground(
            myUserName.value,
            myPassword.value
        ) { parseUser, parseException ->
            if (parseUser != null) {
                onSuccess()
            } else {
                ParseUser.logOut()
                onError(parseException.message ?: "Fatal error")
            }
        }
    }

    fun onSignInClick(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        if (myUserName.value.isNotEmpty() && myPassword.value.isNotEmpty()) {
            val user = ParseUser().apply {
                username = myUserName.value
                setPassword(myPassword.value)
            }
            user.signUpInBackground { exception ->
                if (exception == null) {
                    loginTheUser(
                        onSuccess = onSuccess,
                        onError = onError
                    )
                } else {
                    ParseUser.logOut()
                    if (exception.message != null) {
                        if (exception.message!!.contains("Account already exists for this username.")) {
                            loginTheUser(
                                onSuccess = onSuccess,
                                onError = onError
                            )
                        } else {
                            onError(exception.message ?: "Fatal error")
                        }
                    }
                }
            }
        }
    }
}