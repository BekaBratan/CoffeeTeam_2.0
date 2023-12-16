package com.example.coffeeproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userLogin: EditText = findViewById(R.id.user_login)
        val userEmail: EditText = findViewById(R.id.user_email)
        val userPass: EditText = findViewById(R.id.user_pass)
        val button: Button = findViewById(R.id.button_reg)

        button.setOnClickListener{
            val login = userLogin.text.toString().trim()
            val email = userEmail.text.toString().trim()
            val pass = userPass.text.toString().trim()

            if(login == "" || email == "" || pass == "")
                Toast.makeText(this, "Not all fields are filled in", Toast.LENGTH_LONG).show()
            else {
                val user = User(login, email, pass)

                val db = DbHelper(this, null)
                db.addUser(user)

                Toast.makeText(this, "User added: $login", Toast.LENGTH_LONG).show()

                userLogin.text.clear()
                userEmail.text.clear()
                userPass.text.clear()
            }
        }

    }
}