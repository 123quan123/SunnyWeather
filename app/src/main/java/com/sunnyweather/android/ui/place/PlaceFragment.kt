package com.sunnyweather.android.ui.place

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sunnyweather.android.R
import com.sunnyweather.android.logic.model.Location
import com.sunnyweather.android.logic.model.Place
import kotlinx.android.synthetic.main.fragment_place.*

class PlaceFragment : Fragment() {
    val placeViewModel by lazy {
        ViewModelProviders.of(this).get(PlaceViewModel::class.java)
    }
    private lateinit var adapter: PlaceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var layoutManager = LinearLayoutManager(activity)
        recycleView.layoutManager = layoutManager
        adapter = PlaceAdapter(this, placeViewModel.placeList)
        recycleView.adapter = adapter
        searchPlaceEdit.addTextChangedListener {
            val content = it.toString()
            if (content.isNotEmpty()) {
                placeViewModel.searchPlaces(content)
            } else {
                recycleView.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                placeViewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }
        placeViewModel.placeLiveData.observe(this, Observer {
            var places = it.getOrNull()
            if (places != null) {
                recycleView.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                placeViewModel.placeList.clear()
                val placess = ArrayList<Place>()
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
//                placess.add(Place("北京", Location("123", "456"), "西区"))
                placeViewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(context, "未查询到任何地点", Toast.LENGTH_SHORT).show()
                it.exceptionOrNull()?.printStackTrace()
            }
        })
    }

}