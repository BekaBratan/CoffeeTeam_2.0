package com.example.coffeeproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoffeeAdapter(var coffees: List<Coffee>, var context: Context) : RecyclerView.Adapter<CoffeeAdapter.MyViewHolder>() {
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.coffee_list_image)
        val title: TextView = view.findViewById(R.id.coffee_list_title)
        val desc: TextView = view.findViewById(R.id.coffee_list_desc)
        val rank: TextView = view.findViewById(R.id.coffee_list_rank)
        val price: TextView = view.findViewById(R.id.coffee_list_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coffee_in_list,parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return coffees.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = coffees[position].title
        holder.desc.text = coffees[position].desc
        holder.rank.text = coffees[position].rank.toString()
        holder.price.text = coffees[position].price.toString()

        var imageId = context.resources.getIdentifier(
            coffees[position].image, "drawable",
            context.packageName
        )

        holder.image.setImageResource(imageId)
    }
}