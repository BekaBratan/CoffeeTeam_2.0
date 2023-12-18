package com.example.coffeeproject

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CoffeeAdapter(var coffees: List<Coffee>, var context: Context) : RecyclerView.Adapter<CoffeeAdapter.MyViewHolder>() {
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.coffee_image)
        val title: TextView = view.findViewById(R.id.coffee_title)
        val desc: TextView = view.findViewById(R.id.coffee_desc)
        val rank: TextView = view.findViewById(R.id.coffee_rank)
        val price: TextView = view.findViewById(R.id.coffee_price)
        val btn: LinearLayout = view.findViewById(R.id.coffee_list_btn)
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

        val image = holder.image.setImageResource(imageId)

        holder.btn.setOnClickListener{
            val intent = Intent(context, CoffeeActivity::class.java)

            intent.putExtra("coffeeId", coffees[position].id.toString())
            intent.putExtra("coffeeTitle", coffees[position].title)
            intent.putExtra("coffeeDesc", coffees[position].desc)
            intent.putExtra("coffeeText", coffees[position].text)
            intent.putExtra("coffeeRank", coffees[position].rank.toString())
            intent.putExtra("coffeePrice", coffees[position].price.toString())
            intent.putExtra("coffeeImageId", imageId.toString())
            intent.putExtra("prev", "shop")

            context.startActivity(intent)
        }
    }
}