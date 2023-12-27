package com.yogi.kmtest.repository

import androidx.lifecycle.liveData
import com.google.gson.Gson
import com.yogi.kmtest.data.remote.response.UserResponse
import com.yogi.kmtest.data.remote.retrofit.ApiService
import com.yogi.kmtest.ui.utils.ResultState
import retrofit2.HttpException

class Repository private constructor(
    private val apiService: ApiService
){

    fun getAllUsers() = liveData {
        emit(ResultState.Loading)
        try {
            val response = apiService.getUsers()
            emit(ResultState.Success(response))
        }catch (e: HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, UserResponse::class.java)
            emit(ResultState.Error(errorResponse?.message.toString()))
        }
    }


    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            apiService: ApiService
        ): Repository =
            instance ?: synchronized(this) {
                instance
                    ?: Repository(apiService)
            }.also { instance = it }
    }
}