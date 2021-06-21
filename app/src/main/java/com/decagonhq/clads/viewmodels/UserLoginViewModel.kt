package com.decagonhq.clads.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads.models.UserLogin
import com.decagonhq.clads.models.UserLoginResponse
import com.decagonhq.clads.models.UserRole
import com.decagonhq.clads.repositories.Repository
import com.decagonhq.clads.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserLoginViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _loginResponseLiveData = MutableLiveData<Resource<UserLoginResponse>>()
    val loginResponseLiveData: LiveData<Resource<UserLoginResponse>> get() = _loginResponseLiveData

    private val _loginWithGoogleLiveData = MutableLiveData<Resource<UserLoginResponse>>()
    val loginWithGoogleLiveData: LiveData<Resource<UserLoginResponse>> get() = _loginWithGoogleLiveData

    fun loginUser(user: UserLogin) {
        viewModelScope.launch {
            _loginResponseLiveData.value = repository.loginUser(user)
        }
    }

    fun loginWithGoogle(header: String, role: UserRole) {
        viewModelScope.launch {
            _loginWithGoogleLiveData.value = repository.loginWithGoogle(header, role)
        }
    }
}
