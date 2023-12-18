package com.example.coffeeproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        val backBtn: TextView = findViewById(R.id.back_btn)
        val cardNum: TextView = findViewById(R.id.user_card_num)
        val cardDate: TextView = findViewById(R.id.user_card_date)
        val cardCSV: TextView = findViewById(R.id.user_csv)
        val address: TextView = findViewById(R.id.user_address)
        val price: TextView = findViewById(R.id.price_label)
        val payBtn: Button = findViewById(R.id.pay_btn)

        val db = DbHelper(this, null)
        price.text = db.getPrice().toString() + " tg"

        backBtn.setOnClickListener{
            val intent = Intent(this, CartActivity::class.java)
            this.startActivity(intent)
        }

        payBtn.setOnClickListener{
            if(cardNum.text.toString().trim() == "" || cardDate.text.toString().trim() == "" || cardCSV.text.toString().trim() == "" || address.text.toString().trim() == ""){
                Toast.makeText(this, "Not all fields are filled in", Toast.LENGTH_LONG).show()
            } else {
                db.clearCart()
                val intent = Intent(this, SuccessActivity::class.java)
                this.startActivity(intent)
            }
        }
    }
}