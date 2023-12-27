package com.yogi.kmtest.di

import com.yogi.kmtest.data.remote.retrofit.ApiConfig
import com.yogi.kmtest.repository.Repository

object Injection {
    fun provideRepository(): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService)
    }
}