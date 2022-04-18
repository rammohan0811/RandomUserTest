package com.example.mvvmdemo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvmdemo.models.User

@Database(entities = [User::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}