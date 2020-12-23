package com.example.auth.ui.main.newActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.data.repositories.RetrofitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewViewModel(private val repository: RetrofitRepository) : ViewModel() {

    val error = MutableLiveData<String>()

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                repository.loadProfile()
            }.onFailure {
                error.postValue(it.localizedMessage)
            }
        }
    }

    fun getProfileModel() = repository.getProfile()
}