package com.susanlama.susan_sample_test_app.apiservice

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Susan Lama on 4/7/20.
 * Email: susan.officiallmailcheck@gmail.com
 */
object RetrofitClient {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    private var retrofit: Retrofit? = null

    val service: ApiDataService
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit!!.create<ApiDataService>(ApiDataService::class.java)
        }
}