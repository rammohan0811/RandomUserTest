package com.example.mvvmdemo.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class User(
    @PrimaryKey(autoGenerate = true) var uid: Int,
    @ColumnInfo(name = "first_name") var firstName: String?,
    @ColumnInfo(name = "last_name") var lastName: String?,
    @ColumnInfo(name = "address") var address: String?
)