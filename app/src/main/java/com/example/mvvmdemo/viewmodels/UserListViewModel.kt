package com.example.mvvmdemo.viewmodels


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmdemo.models.Users
import com.example.mvvmdemo.repositories.MainRepository
import com.example.mvvmdemo.utilities.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val mainRepo: MainRepository) : ViewModel() {

    var users: MutableLiveData<Users?> = MutableLiveData()

    fun getPagedUsersList(pageNumber: Int, pageSize: Int) {
        mainRepo.getUsersList(pageNumber, pageSize).enqueue(object : Callback<Users> {

            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                val userData: Users? = response.body()
                Log.e("Request_URL", call.request().url.toString())
                if (userData != null) {
                    users.value = userData
                }
                Utils.dismissProgressDialog()
            }

            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.e("Request_URL", "No response")
                Utils.dismissProgressDialog()
            }
        })
    }
}