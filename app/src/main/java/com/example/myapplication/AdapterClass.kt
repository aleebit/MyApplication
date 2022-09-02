package com.example.myapplication


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class AdapterClass internal constructor(private val itemsList: List<JsonDataClass?>?) :
    RecyclerView.Adapter<AdapterClass.MyViewHolder>() {

    //connect layout file with recycleView adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view =
            layoutInflater.inflate(R.layout.item_row, parent, false)
        return MyViewHolder(view)
    }

    //show data
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList?.get(position)
        if (!item?.name.isNullOrEmpty()) {
            val name =
                "List Id: " + item?.listId.toString() + " = Name: " + item?.name
            holder.name.text = name
        }
    }

    //return total object inside a list
    override fun getItemCount(): Int {
        return itemsList?.size!!
    }

    //view holder pattern help to reuse the xml file
    inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.textView)

    }
}