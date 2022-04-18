package com.example.mvvmdemo.base

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding


abstract class BaseActivity<T : ViewBinding /*, V : ViewModel*/> : AppCompatActivity() {

    protected lateinit var mBinding: T

    //    protected lateinit var mViewModel: V
    protected lateinit var mActivity: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        mBinding = initBinding()
        //  mViewModel = initViewModel()
        setContentView(mBinding.root)
        onReady(savedInstanceState)
    }

    protected abstract fun onReady(savedInstanceState: Bundle?)
    protected abstract fun initBinding(): T
    protected fun showLog(msg: String) = Log.d("Test", msg)
    protected fun showMsg(msg: String) = Toast.makeText(mActivity, msg, Toast.LENGTH_SHORT).show()


    //   protected abstract fun initViewModel(): V

}


