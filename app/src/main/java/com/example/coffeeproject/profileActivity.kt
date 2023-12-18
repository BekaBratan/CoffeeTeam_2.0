package com.example.coffeeproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class profileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val shopBtn: Button = findViewById(R.id.shop_btn)
        val cartBtn: Button = findViewById(R.id.cart_btn)
        val profileBtn: Button = findViewById(R.id.profile_btn)
        val exitBtn: Button = findViewById(R.id.exitBtn)

        exitBtn.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

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

    }

}