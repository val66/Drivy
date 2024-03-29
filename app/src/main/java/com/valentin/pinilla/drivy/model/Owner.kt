package com.valentin.pinilla.drivy.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Owner(
	@SerializedName("name") val name: String,
	@SerializedName("picture_url") val pictureUrl : String,
	@SerializedName("rating") val rating: Rating
) : Serializable