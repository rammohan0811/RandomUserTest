package com.example.mvvmdemo.api

data class ApiResponse<T>(var data: T?, var message: String?, var status: Boolean?)