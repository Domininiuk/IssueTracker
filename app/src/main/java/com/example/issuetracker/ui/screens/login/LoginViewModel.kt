package com.example.issuetracker.ui.screens.login

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import com.example.issuetracker.IssueTrackerViewModel
import com.example.issuetracker.common.extensions.isValidEmail
import com.example.issuetracker.common.extensions.isValidPassword
import com.example.issuetracker.common.snackbar.SnackbarManager
import com.example.issuetracker.common.snackbar.SnackbarMessage
import com.example.issuetracker.model.service.interfaces.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.issuetracker.R.string as AppText

@HiltViewModel
class LoginViewModel @Inject constructor(var accountService: AccountService)
    : IssueTrackerViewModel()
{
    var uiState = mutableStateOf(LoginUiState())

    fun onPasswordChange(password: String) {
        uiState.value = uiState.value.copy(password = password)
    }

    fun onEmailChange(email: String) {
      uiState.value = uiState.value.copy(email = email)

    }

    fun onSignInPressed() {
        val email = uiState.value.email
        val password = uiState.value.password

        if(!email.isValidEmail()){
            SnackbarManager.showMessage(AppText.email_error)
            Log.d("LoginScreen", "Invalid email")

        }
        if(!password.isValidPassword())
        {
          //  SnackbarManager.showMessage(AppText.password_error)
            Log.d("LoginScreen", "Invalid password")

        }
        else
        {
            accountService.authenticate(uiState.value.email, uiState.value.password) {
                if(it == null)
                {
                    Log.d("Firebase", "Login in successful")
                }
                else {
                    Log.d("Firebase", "Login in NOT successful")
                }
            }
        }

    }

    fun onForgotPasswordPressed() {
      //  TODO("Not yet implemented")
    }

    fun onCreateNewAccountPressed() {
//        TODO("Not yet implemented")
    }


}