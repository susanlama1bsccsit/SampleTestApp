package com.susanlama.susan_sample_test_app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.susanlama.susan_sample_test_app.model.usermodel.UserModelItem
import com.susanlama.susan_sample_test_app.repository.UserRepository

/**
 * Created by Susan Lama on 4/7/20.
 * Email: susan.officiallmailcheck@gmail.com
 */
class UserViewModel (application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository = UserRepository()

    val allUser: MutableLiveData<List<UserModelItem>>
        get() = userRepository.getMutableLiveData()

}