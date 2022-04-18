package com.example.mvvmdemo.models

data class Login(val sha: String = "",
                 val password: String = "",
                 val salt: String = "",
                 val uuid: String = "",
                 val username: String = "",
                 val md: String = "")