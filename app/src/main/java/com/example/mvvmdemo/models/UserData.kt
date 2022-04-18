package com.example.mvvmdemo.models

data class UserData(var users: List<UserListData>? = null, var has_more: Boolean = false)

data class UserListData(
    val name: String?,
    val image: String?,
    val items: List<String>?
)

