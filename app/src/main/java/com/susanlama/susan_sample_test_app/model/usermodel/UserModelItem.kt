package com.susanlama.susan_sample_test_app.model.usermodel

import com.google.gson.annotations.SerializedName
import com.susanlama.susan_sample_test_app.model.usermodel.Address
import com.susanlama.susan_sample_test_app.model.usermodel.Company

data class UserModelItem(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("phone")
    val phone: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("username")
    val userName: String,

    @SerializedName("website")
    val webSite: String,

    @SerializedName("address")
    val address: Address,

    @SerializedName("company")
    val company: Company
)