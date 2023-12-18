package com.example.coffeeproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class CoffeeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee)

        val title: TextView = findViewById(R.id.coffee_title)
        val desc: TextView = findViewById(R.id.coffee_desc)
        val rank: TextView = findViewById(R.id.coffee_rank)
        val price: TextView = findViewById(R.id.coffee_price)
        val text: TextView = findViewById(R.id.coffee_text)
        val image: ImageView = findViewById(R.id.coffee_image)
        val addToCartBtn: Button = findViewById(R.id.coffee_buy)
        val backBtn: TextView = findViewById(R.id.back_btn)

        val coffeeId = intent.getStringExtra("coffeeId")?.toInt()
        title.text = intent.getStringExtra("coffeeTitle")
        desc.text = intent.getStringExtra("coffeeDesc")
        rank.text = intent.getStringExtra("coffeeRank") + " ⭐"
        price.text = intent.getStringExtra("coffeePrice") + " tg"
        text.text = intent.getStringExtra("coffeeText")
        val imageId: Int = intent.getStringExtra("coffeeImageId")?.toInt() ?: 0
        image.setImageResource(imageId)

        val prev = intent.getStringExtra("prev")

        val db = DbHelper(this, null)
        val coffee = coffees[coffeeId!!]

        addToCartBtn.setOnClickListener{
            db.addCart(coffee)
        }

        backBtn.setOnClickListener{
            val intent: Intent
            if (prev == "shop")
                intent = Intent(this, CoffeesActivity::class.java)
            else
                intent = Intent(this, CartActivity::class.java)
            this.startActivity(intent)
        }
    }
}