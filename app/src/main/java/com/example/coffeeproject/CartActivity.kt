package com.example.coffeeproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val shopBtn: Button = findViewById(R.id.shop_btn)
        val cartBtn: Button = findViewById(R.id.cart_btn)
        val profileBtn: Button = findViewById(R.id.profile_btn)
        val paymentBtn: Button = findViewById(R.id.payment_btn)

        shopBtn.setOnClickListener{
            val intent = Intent(this, CoffeesActivity::class.java)
            startActivity(intent)
        }

        cartBtn.setOnClickListener{
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        profileBtn.setOnClickListener{
            val intent = Intent(this, profileActivity::class.java)
            startActivity(intent)
        }

        paymentBtn.setOnClickListener{
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }

        val cartList: RecyclerView = findViewById(R.id.cart_list)

        val db = DbHelper(this, null)
        val cart = db.getCart()
        val coffeeInCart = mutableListOf<Coffee>()

        if (cart != null)
            for (i in 0..<cart.size){
                val count = cart[i]
                for (j in 0..<count!!) {
                    coffeeInCart.add(coffees[i])
                }
            }

        cartList.layoutManager = LinearLayoutManager(this)
        cartList.adapter = CartAdapter(coffeeInCart, this)
    }
}