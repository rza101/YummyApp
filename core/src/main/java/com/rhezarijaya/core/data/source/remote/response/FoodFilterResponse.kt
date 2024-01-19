package com.rhezarijaya.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodFilterResponse(
    @field:SerializedName("meals")
    val data: List<FoodFilterItemResponse>
) : Parcelable