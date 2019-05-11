package com.reece.gogolookhomework.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Repository {

    suspend fun search(key: String, q: String, imageType: String) : Model.Result? {
        return withContext(Dispatchers.Main) {
            val apiService = ApiService.create()
            val result = apiService.search(key, q, imageType).await().body()

            result
        }
    }
}