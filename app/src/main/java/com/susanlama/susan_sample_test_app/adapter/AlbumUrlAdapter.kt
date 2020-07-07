package com.susanlama.susan_sample_test_app.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.susanlama.susan_sample_test_app.R
import com.susanlama.susan_sample_test_app.databinding.AlbumAdapterBinding
import com.susanlama.susan_sample_test_app.model.imagemodel.AlbumUrlModelItem
import java.util.*


/**
 * Created by Susan Lama on 4/7/20.
 * Email: susan.officiallmailcheck@gmail.com
 */
class AlbumUrlAdapter(context: Context) : RecyclerView.Adapter<AlbumUrlAdapter.AlbumViewHolder>() {

    private var context = context
    private val clickHandler: AlbumUrlAdapter.ClickEventHandler = context as AlbumUrlAdapter.ClickEventHandler

    private var mAlbumUrlModelItem = ArrayList<AlbumUrlModelItem>()
    private var filteredAlbumUrlModelItem = ArrayList<AlbumUrlModelItem>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): AlbumViewHolder {
        val albumListBinding = DataBindingUtil.inflate<AlbumAdapterBinding>(
            LayoutInflater.from(viewGroup.context),
            R.layout.album_adapter, viewGroup, false
        )

        return AlbumViewHolder(albumListBinding).also {
            albumListBinding.root.setOnClickListener(View.OnClickListener {
                clickHandler.forwardClick(albumListBinding.albumId!!,
                    albumListBinding.photoId!!,
                    albumListBinding.title!!,
                    albumListBinding.url!!,
                    it)
            })
        }
    }

    override fun onBindViewHolder(mAlbumViewHolder: AlbumViewHolder, i: Int) {
        val currentAlbum = mAlbumUrlModelItem[i]

        loadImage(mAlbumViewHolder.albumListBinding.ivAlbum, currentAlbum.thumbnailUrl)
        mAlbumViewHolder.albumListBinding.title = currentAlbum.title
        mAlbumViewHolder.albumListBinding.albumUrl = currentAlbum.thumbnailUrl
        mAlbumViewHolder.albumListBinding.albumId = currentAlbum.albumId.toString()
        mAlbumViewHolder.albumListBinding.photoId = currentAlbum.id.toString()
        mAlbumViewHolder.albumListBinding.url = currentAlbum.url.toString()


    }

    override fun getItemCount(): Int {
        return if (mAlbumUrlModelItem != null) {
            mAlbumUrlModelItem.size
        } else {
            0
        }
    }

    private fun loadImage(view: ImageView, imageUrl: String) {
        Log.d("Album Adapter: ", "URL: " + imageUrl)

        context?.let {
            Picasso.get()
                .load(imageUrl)
                .into(view)
        }
    }

    fun setAlbumList(userId: Int, mAlbumUrlModelItem: ArrayList<AlbumUrlModelItem>) {


        for (i in 0 until mAlbumUrlModelItem.size) {
            var albumUrlModelItem = AlbumUrlModelItem(0, 0, "", "", "")

            if (userId == mAlbumUrlModelItem[i].albumId) {

                albumUrlModelItem.albumId = mAlbumUrlModelItem[i].albumId
                albumUrlModelItem.id = mAlbumUrlModelItem[i].id
                albumUrlModelItem.thumbnailUrl = mAlbumUrlModelItem[i].thumbnailUrl
                albumUrlModelItem.url = mAlbumUrlModelItem[i].url
                albumUrlModelItem.title = mAlbumUrlModelItem[i].title
                filteredAlbumUrlModelItem.add(albumUrlModelItem)

                Log.d(
                    "Album Adapter: ",
                    "ID: $userId = ${albumUrlModelItem.albumId} == ${albumUrlModelItem.title}"
                )
            }

        }

        Log.d("Album Adapter: ", "Size: " + filteredAlbumUrlModelItem.size)

        this.mAlbumUrlModelItem = filteredAlbumUrlModelItem
        this.notifyDataSetChanged()
    }


    interface ClickEventHandler {
        fun forwardClick(albumId: String?, photoId: String?, title: String?, url: String?, holder: View)
    }

    inner class AlbumViewHolder(var albumListBinding: AlbumAdapterBinding) :
        RecyclerView.ViewHolder(albumListBinding.root)
}
