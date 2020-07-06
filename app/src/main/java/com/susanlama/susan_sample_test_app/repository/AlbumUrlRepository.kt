package com.susanlama.susan_sample_test_app.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.susanlama.susan_sample_test_app.apiservice.RetrofitClient
import com.susanlama.susan_sample_test_app.model.imagemodel.AlbumUrlModelItem
import com.susanlama.susan_sample_test_app.model.usermodel.UserModelItem

import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Susan Lama on 4/7/20.
 * Email: susan.officiallmailcheck@gmail.com
 */
class AlbumUrlRepository() {

    private val albumModelList = ArrayList<AlbumUrlModelItem>()
    private var albumUriMutableLiveData = MutableLiveData<List<AlbumUrlModelItem>>()

    ////call to internet and  return  MutableLiveData
    fun getAlbumUriMutableLiveData(): MutableLiveData<List<AlbumUrlModelItem>> {

        ///ini Retrofit Class
        val userDataService = RetrofitClient.service

        ///call the API that define In Interface
        val call = userDataService.apiRequestsArrayAlbumUrl

        call?.enqueue(object : Callback<ArrayList<AlbumUrlModelItem>> {
            override fun onResponse(
                call: Call<ArrayList<AlbumUrlModelItem>>,
                response: Response<ArrayList<AlbumUrlModelItem>>?
            ) {
                if (response != null) {
                    albumModelList.addAll(response.body()!!)
                    Log.d(TAG, "Api Album response success..." + response.body())
                    Log.d(TAG, "Size: " + albumModelList.size)
                    albumUriMutableLiveData.value = albumModelList

                }

            }

            override fun onFailure(call: Call<ArrayList<AlbumUrlModelItem>>, t: Throwable) {
                Log.e(TAG, "Failed to fetch data... ${t.message}")

            }

        })

        return albumUriMutableLiveData
    }

    companion object {

        private val TAG = "Album Repository"
    }
}

