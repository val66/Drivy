package com.valentin.pinilla.drivy

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.valentin.pinilla.drivy.carList.CarListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayCarList(savedInstanceState)
    }

    private fun displayCarList(savedInstanceState: Bundle?) {

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_holder, CarListFragment.newInstance(), "carList")
                .commit()
        }
    }
}
