package com.sunnyweather.android.ui.place

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sunnyweather.android.R
import com.sunnyweather.android.logic.model.PlaceResponse
import com.sunnyweather.android.ui.place.PlaceAdapter.ViewHolder

class PlaceAdapter(val fragment: Fragment, val placeList: List<PlaceResponse.Place>) :
    RecyclerView.Adapter<ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val placeName : TextView = view.findViewById(R.id.placename)
        val placeAddress : TextView = view.findViewById(R.id.placeAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(fragment.context).inflate(R.layout.place_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.placeName.text = place.name
        holder.placeAddress.text = place.address
    }

    override fun getItemCount(): Int {
        return placeList.size
    }
}