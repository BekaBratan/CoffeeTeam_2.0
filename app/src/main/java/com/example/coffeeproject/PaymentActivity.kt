package com.example.coffeeproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        val backBtn: TextView = findViewById(R.id.back_btn)
        val price: TextView = findViewById(R.id.price_label)
        val payBtn: Button = findViewById(R.id.pay_btn)

        val db = DbHelper(this, null)
        price.text = db.getPrice().toString() + " tg"

        backBtn.setOnClickListener{
            val intent = Intent(this, CartActivity::class.java)
            this.startActivity(intent)
        }

        payBtn.setOnClickListener{
            val intent = Intent(this, CartActivity::class.java)
            this.startActivity(intent)
        }
    }
}