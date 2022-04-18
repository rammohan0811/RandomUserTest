package com.example.mvvmdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mvvmdemo.models.User


@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUsers(): LiveData<List<User>>

    @Insert
    suspend fun insertUser(users: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Update
    suspend fun updateUser(user: User)

}