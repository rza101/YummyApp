package com.rhezarijaya.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodCategoryItemResponse(
    @field:SerializedName("idCategory")
    val id: String,

    @field:SerializedName("strCategory")
    val name: String,

    @field:SerializedName("strCategoryDescription")
    val description: String?,

    @field:SerializedName("strCategoryThumb")
    val thumbnailImage: String?
) : Parcelable