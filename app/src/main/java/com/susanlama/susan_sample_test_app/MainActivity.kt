package com.susanlama.susan_sample_test_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.susanlama.susan_sample_test_app.adapter.UserInfoAdapter
import com.susanlama.susan_sample_test_app.databinding.ActivityMainBinding
import com.susanlama.susan_sample_test_app.model.usermodel.UserModelItem
import com.susanlama.susan_sample_test_app.viewmodel.UserViewModel
import java.util.*

class MainActivity : AppCompatActivity(), UserInfoAdapter.ClickEventHandler {

    internal var activityMainBinding: ActivityMainBinding?= null
    internal var loadBar : ProgressBar? = null
    var mainViewModel: UserViewModel? = null
    private var userAdapter: UserInfoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        // bind RecyclerView
        val recyclerView = activityMainBinding?.viewUser
        loadBar = activityMainBinding!!.loadBar
        recyclerView!!.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        ///init the View Model
        mainViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        //init the Custom adapter
        userAdapter = UserInfoAdapter(this)

        //set the CustomAdapter
        recyclerView.adapter = userAdapter

        //get All User info
        getAllUserList()

    }

    private fun getAllUserList() {
        ///get the list of users from api response
        mainViewModel!!.allUser.observe(this,
            Observer<List<Any>> { userModel ->
                ///if any thing changes update to UI
                userAdapter?.setUserList(userModel as ArrayList<UserModelItem>)
                loadBar?.visibility = View.GONE
            })
    }

    override fun forwardClick(id: String, holder: View) {
        val intent = Intent(this, AlbumListActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)

    }

    companion object{

        private val TAG = "Main Activity"
        const val EXTRA_ID = "userId"
    }
}