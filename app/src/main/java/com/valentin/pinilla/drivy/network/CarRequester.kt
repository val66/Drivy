package com.valentin.pinilla.drivy.network

import com.valentin.pinilla.drivy.model.Car
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.collections.ArrayList

class CarRequester {

    private val url = "https://raw.githubusercontent.com/drivy/jobs/"
    private var retrofit : Retrofit
    private var listeners : ArrayList<CarListener> = ArrayList()

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getCars(listener: CarListener) {

        if (!listeners.contains(listener)){
            listeners.add(listener)
        }

        val service = retrofit.create(CarService::class.java)
        val carRequest = service.cars()

        carRequest.enqueue(object : Callback<List<Car>> {
            override fun onResponse(call: Call<List<Car>>, response: Response<List<Car>>) {
                val allCars = response.body()

                if (allCars != null) {
                    for (storedListener in listeners){
                        listener.onCarsReceived(allCars)
                    }
                }
            }
            override fun onFailure(call: Call<List<Car>>, t: Throwable) {
                for (storedListener in listeners){
                    listener.onError()
                }
            }
        })
    }

    interface CarListener{
        fun onCarsReceived(allCars: List<Car>?)
        fun onError()
    }

}