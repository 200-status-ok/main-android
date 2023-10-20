package com.example.haminjast.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haminjast.data.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private val _otpState = MutableStateFlow(OTPState.NOT_REQUESTED)
    val otpState = _otpState.asStateFlow()

    fun sendOTP(userName: String) {
        if (!validateUserName(userName)) return
        viewModelScope.launch {
            _otpState.update {
                OTPState.REQUESTED
            }
            val sendOTPResult = loginRepository.sendOTP(userName)
            if (sendOTPResult.isSuccess) {
                _otpState.update {
                    OTPState.SENT
                }
            } else {
                _otpState.update {
                    OTPState.NOT_REQUESTED
                }
            }
        }
    }

    fun onUserNameChanged(userName:String){
        _userName.update { userName }
    }

    private fun validateUserName(userName: String): Boolean {
        return userName.isNotEmpty()
    }

    enum class OTPState {
        NOT_REQUESTED,
        REQUESTED,
        SENT,
    }

}