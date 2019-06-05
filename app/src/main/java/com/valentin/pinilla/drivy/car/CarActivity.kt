package com.valentin.pinilla.drivy.car

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.valentin.pinilla.drivy.R
import com.valentin.pinilla.drivy.carList.RecyclerAdapter.CarHolder.Companion.CAR_KEY
import com.valentin.pinilla.drivy.model.Car
import kotlinx.android.synthetic.main.car_resume.view.*

class CarActivity : AppCompatActivity() {

    private var selectedCar: Car? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_car)
        selectedCar = intent.getSerializableExtra(CAR_KEY) as Car

        handleBackNavigation()
        displayCarInformation()
        displayOwnerInformation()
    }

    private fun handleBackNavigation() {
        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    private fun displayCarInformation() {

        val carImage = findViewById<ImageView>(R.id.car_image)
        val carName = findViewById<TextView>(R.id.car_name)
        val carPrice = findViewById<TextView>(R.id.car_price)
        val carRating = findViewById<RatingBar>(R.id.car_rating)
        val carRatingCount = findViewById<TextView>(R.id.car_rating_count)

        Picasso.with(this).load(selectedCar!!.pictureUrl).fit().centerCrop().into(carImage)
        carName.text = getString(R.string.car_name, selectedCar!!.brand, selectedCar!!.model)
        carPrice.car_price.text = getString(R.string.car_price, selectedCar!!.pricePerDay)
        carRating.rating = selectedCar!!.rating.average
        carRatingCount.car_rating_count.text = selectedCar!!.rating.count.toString()
    }

    private fun displayOwnerInformation() {

        val ownerImage = findViewById<ImageView>(R.id.owner_image)
        val ownerName = findViewById<TextView>(R.id.owner_name)
        val ownerRate = findViewById<RatingBar>(R.id.owner_rating)

        Picasso.with(this).load(selectedCar!!.owner.pictureUrl).fit().centerCrop().into(ownerImage)
        ownerName.text = selectedCar!!.owner.name
        ownerRate.rating = selectedCar!!.owner.rating.average
    }
}