package com.example.mvvmdemo.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmdemo.api.ApiResponse
import com.example.mvvmdemo.api.NetworkState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

open class BaseRepository {
    val mNetworkState = MutableLiveData<NetworkState>()
    fun <T> call(call: Call<ApiResponse<T>>): LiveData<T> {
        val liveData = MutableLiveData<T>()
        mNetworkState.postValue(NetworkState.LOADING)
        call.enqueue(object : Callback<ApiResponse<T>> {
            override fun onResponse(call: Call<ApiResponse<T>>, response: Response<ApiResponse<T>>) {
                if (response.body() != null) {
                    if (response.isSuccessful && response.body()?.status == true) {
                        liveData.value = response.body()?.data!!
                        mNetworkState.postValue(NetworkState.LOADED)
                    } else {
                        mNetworkState.postValue(NetworkState.error(response.message()))
                    }
                } else {
                    mNetworkState.postValue(NetworkState.error("No Data found"))
                }
            }

            override fun onFailure(call: Call<ApiResponse<T>>, t: Throwable) {
                val errorMsg: String? = if (t is IOException)
                    "No Internet"
                else
                    t.message

                mNetworkState.postValue(NetworkState.error(errorMsg))
            }
        })
        return liveData
    }
}