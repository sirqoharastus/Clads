package com.decagonhq.clads.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decagonhq.clads.models.User
import com.decagonhq.clads.models.UserRegistrationResponse
import com.decagonhq.clads.repositories.Repository
import com.decagonhq.clads.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRegistrationViewModel @Inject constructor(private val repository: Repository) :
    ViewModel() {

    private val _user: MutableLiveData<Resource<UserRegistrationResponse>> = MutableLiveData()
    val user: LiveData<Resource<UserRegistrationResponse>> get() = _user

    fun registerUser(user: User) {

        viewModelScope.launch {
            Log.i("Retrofit_Request", "WOOORKKKINGG")
            _user.value = repository.registerUser(user)
        }
    }
}
