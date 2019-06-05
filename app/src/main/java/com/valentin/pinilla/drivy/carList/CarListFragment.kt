package com.valentin.pinilla.drivy.carList

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.valentin.pinilla.drivy.R
import com.valentin.pinilla.drivy.model.Car
import com.valentin.pinilla.drivy.network.CarRequester
import kotlin.collections.ArrayList

class CarListFragment : Fragment(), CarRequester.CarListener {
    private var carList: ArrayList<Car> = ArrayList()
    private var carRequester: CarRequester = CarRequester()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: RecyclerAdapter
    private lateinit var loadingView : View
    private lateinit var errorView : View
    private lateinit var recyclerView: RecyclerView

    companion object {

        fun newInstance(): CarListFragment {
            return CarListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_car_list, container, false)

        setRecyclerView(view)
        setLoadingView(view)
        setErrorView(view)

        carRequester.getCars(this)
        loadingView.visibility = View.VISIBLE

        return view
    }

    private fun setLoadingView(view: View) {
        loadingView = view.findViewById(R.id.loading_view)
        loadingView.findViewById<TextView>(R.id.loading_message).text = getString(R.string.loading_car_message)
    }

    private fun setErrorView(view: View) {
        errorView = view.findViewById(R.id.error_view)
        errorView.visibility = View.GONE
        errorView.findViewById<TextView>(R.id.error_message).text = getString(R.string.error_loading_car_message)
        errorView.findViewById<Button>(R.id.retry_button).setOnClickListener {
            errorView.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            carRequester.getCars(this)
        }
    }

    private fun setRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.car_recycler_view)

        linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        adapter = RecyclerAdapter(carList)
        recyclerView.adapter = adapter
        recyclerView.visibility = View.INVISIBLE
    }

    override fun onCarsReceived(allCars: List<Car>?) {
        activity!!.runOnUiThread {
            carList.addAll(allCars!!)
            adapter.notifyItemInserted(carList.size-1)
            loadingView.visibility = View.GONE
            errorView.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    override fun onError() {
        loadingView.visibility = View.GONE
        errorView.visibility = View.VISIBLE
    }
}