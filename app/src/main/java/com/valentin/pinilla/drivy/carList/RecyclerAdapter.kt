package com.valentin.pinilla.drivy.carList

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.valentin.pinilla.drivy.R
import com.valentin.pinilla.drivy.car.CarActivity
import com.valentin.pinilla.drivy.inflate
import com.valentin.pinilla.drivy.model.Car
import kotlinx.android.synthetic.main.car_resume.view.*
import kotlinx.android.synthetic.main.recyclerview_car_row.view.*


class RecyclerAdapter(private val cars: List<Car>) : RecyclerView.Adapter<RecyclerAdapter.CarHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarHolder {
        val inflatedView = parent.inflate(R.layout.recyclerview_car_row, false)
        return CarHolder(inflatedView)
    }

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: CarHolder, position: Int) {
        val itemPhoto = cars[position]
        holder.bindPhoto(itemPhoto)
    }

    class CarHolder(private val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        private var car: Car? = null
        private val context = itemView.context

        init {
            view.setOnClickListener(this)
        }

        fun bindPhoto(car: Car) {
            this.car = car
            Picasso.with(view.context).load(car.pictureUrl).fit().centerCrop().into(view.car_image)
            view.car_name.text = context.getString(R.string.car_name, car.brand, car.model)
            view.car_price.text = context.getString(R.string.car_price, car.pricePerDay)
            view.car_rating.rating = car.rating.average
            view.car_rating_count.text = car.rating.count.toString()
        }

        override fun onClick(v: View?) {

            val showPhotoIntent = Intent(context, CarActivity::class.java)
            showPhotoIntent.putExtra(CAR_KEY, car)
            context.startActivity(showPhotoIntent)
        }

        companion object {
            val CAR_KEY = "CAR"
        }
    }
}