package com.valentin.pinilla.drivy.model

import com.google.gson.annotations.SerializedName


data class Car(
	@SerializedName("brand") val brand: String,
	@SerializedName("model") val model: String,
	@SerializedName("picture_url") val pictureUrl: String,
	@SerializedName("price_per_day") val pricePerDay: Int,
	@SerializedName("rating") val rating: Rating,
	@SerializedName("owner") val owner: Owner
)