package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName


class Place(val name : String, val location : Location, @SerializedName("formatted_address")val address : String) {
}
