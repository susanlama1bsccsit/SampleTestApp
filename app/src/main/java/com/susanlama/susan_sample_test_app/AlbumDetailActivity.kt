package com.susanlama.susan_sample_test_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import com.susanlama.susan_sample_test_app.databinding.ActivityAlbumDetailBinding

class AlbumDetailActivity : AppCompatActivity() {

    internal var binding: ActivityAlbumDetailBinding? = null

    private var albumId: String? = null
    private var photoId: String? = null
    private var url: String? = null
    private var title: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_album_detail)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        binding!!.toolbar.setNavigationOnClickListener { onBackPressed() }

        //get values from intent
        albumId = intent.getStringExtra(EXTRA_ALBUM_ID)
        photoId = intent.getStringExtra(EXTRA_PHOTO_ID)
        url = intent.getStringExtra(EXTRA_URL)
        title = intent.getStringExtra(EXTRA_TITLE)

        //set values to views
        binding!!.albumId = "Album ID: $albumId"
        binding!!.photoId = "Photo ID: $photoId"
        binding!!.title = title
        loadImage(binding!!.ivImage, url!!)

    }

    private fun loadImage(view: ImageView, imageUrl: String) {

        this?.let {
            Picasso.get()
                .load(imageUrl)
                .into(view)
        }
    }

    companion object {
        private val TAG = "Album Detail Activity"
        const val EXTRA_ALBUM_ID = "albumId"
        const val EXTRA_PHOTO_ID = "photoId"
        const val EXTRA_URL = "url"
        const val EXTRA_TITLE = "title"


    }
}