package com.example.mvvmdemo.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.viewbinding.ViewBinding
import com.example.mvvmdemo.R

abstract class BaseDialog<T : ViewBinding>(context: Context) : Dialog(context, R.style.ThemeDialog) {
    protected lateinit var mBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(true)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        mBinding = initBinding()
        setContentView(mBinding.root)
        onReady(savedInstanceState)
    }


    protected abstract fun onReady(savedInstanceState: Bundle?)
    
    protected abstract fun initBinding(): T
}