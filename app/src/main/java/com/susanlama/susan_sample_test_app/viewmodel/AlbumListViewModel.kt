package com.susanlama.susan_sample_test_app.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.susanlama.susan_sample_test_app.model.imagemodel.AlbumUrlModelItem
import com.susanlama.susan_sample_test_app.repository.AlbumUrlRepository

/**
 * Created by Susan Lama on 5/7/20.
 * Email: susan.officiallmailcheck@gmail.com
 */
class AlbumListViewModel() : ViewModel() {
    private val albumRepository: AlbumUrlRepository = AlbumUrlRepository()

    val allAlbum: MutableLiveData<List<AlbumUrlModelItem>>
        get() = albumRepository.getAlbumUriMutableLiveData()

}