package com.example.mvvmdemo.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.NestedScrollView
import com.example.mvvmdemo.base.BaseActivity
import com.example.mvvmdemo.databinding.ActivityUserListBinding
import com.example.mvvmdemo.ui.adapters.UserAdapter
import com.example.mvvmdemo.utilities.Utils
import com.example.mvvmdemo.viewmodels.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListActivity : BaseActivity<ActivityUserListBinding>() {

    private var page = 1
    private var limit = 10
    private var userAdapter: UserAdapter? = null
    private var currentPage = 0

    private val mViewModel: UserListViewModel by viewModels()

    override fun initBinding() = ActivityUserListBinding.inflate(layoutInflater)

    override fun onReady(savedInstanceState: Bundle?) {
        mBinding.nestedScrollview.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (v != null && scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                getUserList()
            }
        })
        getUserList()
    }

    private fun getUserList() {
        mViewModel.getPagedUsersList(page, limit)
        Utils.showProgressDialog(this)

        mViewModel.users.observe(mActivity) {
            if (page != currentPage) {
                if (it?.info != null && page <= it.info.page) {
                    if (userAdapter == null) {
                        userAdapter = UserAdapter(this)
                        userAdapter?.setUserList(it.results)
                        mBinding.rvUsers.adapter = userAdapter
                    } else {
                        userAdapter?.addUserListData(it.results)
                        userAdapter?.notifyDataSetChanged()
                    }
                    currentPage = page
                    page += 1
                }
            }
        }
    }
}