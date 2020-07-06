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
import com.susanlama.susan_sample_test_app.adapter.AlbumUrlAdapter
import com.susanlama.susan_sample_test_app.databinding.ActivityAlbumListBinding
import com.susanlama.susan_sample_test_app.model.imagemodel.AlbumUrlModelItem
import com.susanlama.susan_sample_test_app.viewmodel.AlbumListViewModel
import java.util.*

/**
 * Created by Susan Lama on 5/7/20.
 * Email: susan.officiallmailcheck@gmail.com
 */
class AlbumListActivity : AppCompatActivity(), AlbumUrlAdapter.ClickEventHandler {

    private lateinit var albumViewModel: AlbumListViewModel
    internal var albumBinding: ActivityAlbumListBinding? = null
    internal var loadBar: ProgressBar? = null
    private var albumAdapter: AlbumUrlAdapter? = null
    private var albumId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        albumBinding = DataBindingUtil.setContentView(this, R.layout.activity_album_list)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        albumBinding!!.toolbar.setNavigationOnClickListener { onBackPressed() }

        //get values from intent
        albumId = intent.getStringExtra(EXTRA_ID)
        albumBinding!!.albumId = "Album ID: $albumId"

        // bind RecyclerView
        val recyclerView = albumBinding?.viewAlbum
        loadBar = albumBinding!!.loadBar
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        recyclerView!!.setHasFixedSize(true)

        ///init the View Model
        albumViewModel = ViewModelProviders.of(this).get(AlbumListViewModel::class.java)

        //init the Custom adapter
        albumAdapter = AlbumUrlAdapter(this)

        //set the CustomAdapter
        recyclerView.setAdapter(albumAdapter)

        getAllAlbumList()
    }

    private fun getAllAlbumList() {
        ///get the list of users from api response
        albumViewModel!!.allAlbum.observe(this,
            Observer<List<Any>> { userModel ->
                ///if any thing changes update to UI

                albumAdapter?.setAlbumList(albumId!!.toInt(),
                    userModel as ArrayList<AlbumUrlModelItem>)
                loadBar?.visibility = View.GONE
            })
    }

    companion object {
        const val EXTRA_ID = "userId"
        const val EXTRA_ALBUM_ID = "albumId"
        const val EXTRA_PHOTO_ID = "photoId"
        const val EXTRA_URL = "url"
        const val EXTRA_TITLE = "title"
    }

    override fun forwardClick(
        albumId: String?,
        photoId: String?,
        title: String?,
        url: String?,
        holder: View
    ) {
        val intent = Intent(this, AlbumDetailActivity::class.java)
        //pass values to AlbumDetailActivity
        intent.putExtra(EXTRA_ALBUM_ID, albumId)
        intent.putExtra(EXTRA_PHOTO_ID, photoId)
        intent.putExtra(EXTRA_TITLE, title)
        intent.putExtra(EXTRA_URL, url)
        startActivity(intent)
    }
}