package com.example.coffeeproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CartAdapter(var coffees: List<Coffee>, var context: Context) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    class CartViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.cart_image)
        val title: TextView = view.findViewById(R.id.cart_title)
        val desc: TextView = view.findViewById(R.id.cart_desc)
        val price: TextView = view.findViewById(R.id.cart_price)
        val btn: ConstraintLayout = view.findViewById(R.id.cart_list_btn)
        val cancel: Button = view.findViewById(R.id.cart_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.coffee_in_cart,parent, false)
        return CartViewHolder(view)
    }

    override fun getItemCount(): Int {
        return coffees.count()
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.title.text = coffees[position].title
        holder.desc.text = coffees[position].desc
        holder.price.text = coffees[position].price.toString() + " tg"

//        var imageId = context.resources.getIdentifier(
//            coffees[position].image, "drawable",
//            context.packageName
//        )
//
//        val image = holder.image.setImageResource(imageId)

        val imageId = coffees[position].image
        Glide.with(context).load(imageId).into(holder.image)



        holder.btn.setOnClickListener{
            val intent = Intent(context, CoffeeActivity::class.java)

            intent.putExtra("coffeeId", coffees[position].id.toString())
            intent.putExtra("coffeeTitle", coffees[position].title)
            intent.putExtra("coffeeDesc", coffees[position].desc)
            intent.putExtra("coffeeText", coffees[position].text)
            intent.putExtra("coffeeRank", coffees[position].rank.toString())
            intent.putExtra("coffeePrice", coffees[position].price.toString())
            intent.putExtra("coffeeImageId", imageId.toString())

            context.startActivity(intent)
        }

        holder.cancel.setOnClickListener{
            val db = DbHelper(context, null)
            db.removeCart(coffees[position])
            val intent = Intent(context, CartActivity::class.java)
            context.startActivity(intent)
        }
    }
}