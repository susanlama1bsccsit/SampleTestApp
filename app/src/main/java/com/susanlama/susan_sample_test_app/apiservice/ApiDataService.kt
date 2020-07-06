package com.susanlama.susan_sample_test_app.apiservice

import com.susanlama.susan_sample_test_app.model.imagemodel.AlbumUrlModelItem
import com.susanlama.susan_sample_test_app.model.usermodel.UserModelItem

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Susan Lama on 4/7/20.
 * Email: susan.officiallmailcheck@gmail.com
 */
interface ApiDataService {

    @get:GET("users")
    val apiRequestsArray: Call<ArrayList<UserModelItem>>

    @get:GET("photos")
    val apiRequestsArrayAlbumUrl: Call<ArrayList<AlbumUrlModelItem>>


}