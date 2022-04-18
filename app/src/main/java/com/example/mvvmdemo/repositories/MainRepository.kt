package com.example.mvvmdemo.repositories

import androidx.lifecycle.LiveData
import com.example.mvvmdemo.api.ApiService
import com.example.mvvmdemo.db.UserDao
import com.example.mvvmdemo.models.User
import com.example.mvvmdemo.models.Users
import retrofit2.Call
import javax.inject.Inject


class MainRepository @Inject constructor(
        private val apiService: ApiService,
        private val userDao: UserDao
) : BaseRepository() {
    /**
     *  Remote calls and operations
     */
    fun getUsersList(pageNumber: Int, numberOfData: Int): Call<Users> {
        return apiService.getUsersList(pageNumber, numberOfData)
    }

    /**
     *  Database or local operations
     */
    fun getUserList(): LiveData<List<User>> {
        return userDao.getUsers()
    }

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}