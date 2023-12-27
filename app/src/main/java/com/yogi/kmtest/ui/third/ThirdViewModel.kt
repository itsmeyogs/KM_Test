package com.yogi.kmtest.ui.third

import androidx.lifecycle.ViewModel
import com.yogi.kmtest.repository.Repository

class ThirdViewModel(private val repository: Repository):ViewModel() {

    fun getAllUsers() = repository.getAllUsers()
}