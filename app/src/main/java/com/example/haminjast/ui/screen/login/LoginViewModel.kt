package com.example.haminjast.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haminjast.data.datastore.LoginDataStore
import com.example.haminjast.data.repository.LoginRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository, private val loginDataStore: LoginDataStore) : ViewModel() {

    private val _userName = MutableStateFlow("")
    val userName = _userName.asStateFlow()

    private val _otp = MutableStateFlow("")
    val otp = _otp.asStateFlow()

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

    suspend fun verifyOTP(userName: String, otp:String) : Boolean{
        if (!validateUserName(userName)) return false
        val loginJob = viewModelScope.async{
            val verifyOTPResult = loginRepository.verifyOTP(userName, otp)
            if (verifyOTPResult.isSuccess) {
                verifyOTPResult.getOrNull()?.token?.let {
                    loginDataStore.saveTokenF(it)
                }
                _otpState.update {
                    OTPState.NOT_REQUESTED
                }
                true
            }else{
                _otpState.update {
                    OTPState.NOT_REQUESTED
                }
                false
            }
        }
        return loginJob.await()
    }

    fun getToken() = loginDataStore.readTokenF()

    fun onUserNameChanged(userName:String){
        _userName.update { userName }
    }

    fun onOtpChanged(otp : String){
        _otp.update { otp }
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