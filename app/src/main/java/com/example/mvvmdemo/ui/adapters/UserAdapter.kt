package com.example.mvvmdemo.ui.adapters

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmdemo.R
import com.example.mvvmdemo.databinding.DialogUserInfoBinding
import com.example.mvvmdemo.databinding.ItemUserBinding
import com.example.mvvmdemo.models.ResultsItem

class UserAdapter(var context:Context) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var mUserList: MutableList<ResultsItem>? = null

    fun setUserList(mUserList: List<ResultsItem>?) {
        this.mUserList = mUserList?.toMutableList()
        notifyDataSetChanged()
        Log.e("","")
    }

    fun addUserListData(mUserListData: List<ResultsItem>?) {
        if (mUserList!=null) {
            mUserList?.addAll(mUserListData!!)
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        mUserList?.get(position)?.let {
            holder.bindTo(it)
            holder.userEmail.text = it.email
            Glide.with(context).load(it?.picture?.large).into(holder.userImage)
            var resultsItem:ResultsItem = it
            holder.itemView.setOnClickListener {
                showUserDialog(resultsItem)
            }
        }
    }

    override fun getItemCount() = mUserList?.size ?: 0

    class UserViewHolder(private val mBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        var userEmail:TextView = mBinding.userEmail
        var userImage:ImageView = mBinding.userImage
        fun bindTo(user: ResultsItem) {
            mBinding.user = user
        }
    }

    private fun showUserDialog(resultsItem: ResultsItem){
        var binding:DialogUserInfoBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.dialog_user_info,null,false)
        var dialog:Dialog = Dialog(context)
        dialog.setContentView(binding.root)
        Glide.with(context).load(resultsItem.picture.large).into(binding.userImage)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.userData = resultsItem
        dialog.show()
    }
}