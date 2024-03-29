package com.valentin.pinilla.drivy.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rating(

	@SerializedName("average") val average: Float,
	@SerializedName("count") val count: Int
) : Serializable