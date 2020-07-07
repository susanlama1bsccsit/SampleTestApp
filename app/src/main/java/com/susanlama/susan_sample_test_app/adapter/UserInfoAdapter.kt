package com.susanlama.susan_sample_test_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.susanlama.susan_sample_test_app.R
import com.susanlama.susan_sample_test_app.databinding.UserInfoAdapterBinding
import com.susanlama.susan_sample_test_app.model.usermodel.UserModelItem
import java.util.ArrayList


/**
 * Created by Susan Lama on 4/7/20.
 * Email: susan.officiallmailcheck@gmail.com
 */
class UserInfoAdapter(context: Context) : RecyclerView.Adapter<UserInfoAdapter.UserInfoViewHolder>() {

    private val clickHandler: ClickEventHandler = context as ClickEventHandler

    private var mUserModelItem: ArrayList<UserModelItem>? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): UserInfoViewHolder {
        var userListBinding = DataBindingUtil.inflate<UserInfoAdapterBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.user_info_adapter, viewGroup, false
        )

        return UserInfoViewHolder(userListBinding).also {
            userListBinding.root.setOnClickListener(View.OnClickListener {
                clickHandler.forwardClick(userListBinding.id!!, it)
            })
        }
    }

    override fun onBindViewHolder(mUserInfoViewHolder: UserInfoViewHolder, i: Int) {
        val currentStudent = mUserModelItem!![i]

        mUserInfoViewHolder.userListBinding.id = "ID: ${currentStudent.id}"
        mUserInfoViewHolder.userListBinding.name = "Name: ${currentStudent.name}"
        mUserInfoViewHolder.userListBinding.email = "Email: ${currentStudent.email}"
        mUserInfoViewHolder.userListBinding.phone = "Phone: ${currentStudent.phone}"

    }

    override fun getItemCount(): Int {
        return if (mUserModelItem != null) {
            mUserModelItem!!.size
        } else {
            0
        }
    }

    fun setUserList(mUserModelItem: ArrayList<UserModelItem>) {
        this.mUserModelItem = mUserModelItem
        notifyDataSetChanged()
    }


    interface ClickEventHandler {
        fun forwardClick(id: String, holder: View)
    }

    inner class UserInfoViewHolder(var userListBinding: UserInfoAdapterBinding) :
        RecyclerView.ViewHolder(userListBinding.root)


}
