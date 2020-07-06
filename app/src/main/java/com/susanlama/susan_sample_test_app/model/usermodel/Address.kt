package com.susanlama.susan_sample_test_app.model.usermodel

import com.google.gson.annotations.SerializedName

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,

    @SerializedName("zipcode")
    val zipCode: String
)