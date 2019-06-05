package com.valentin.pinilla.drivy.network

import com.valentin.pinilla.drivy.model.Car
import retrofit2.Call
import retrofit2.http.GET

interface CarService {
    @GET("master/android/api/cars.json")
    fun cars(): Call<List<Car>>
}