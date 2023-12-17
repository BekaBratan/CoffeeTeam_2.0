package com.example.coffeeproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

public var currentUser: String = ""
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val userLogin: EditText = findViewById(R.id.user_login)
        val userPass: EditText = findViewById(R.id.user_pass)
        val button: Button = findViewById(R.id.button_reg)
        val linkToReg: TextView = findViewById(R.id.link_to_reg)

        button.setOnClickListener{
            val login = userLogin.text.toString().trim()
            val pass = userPass.text.toString().trim()

            if(login == "" || pass == "")
                Toast.makeText(this, "Not all fields are filled in", Toast.LENGTH_LONG).show()
            else {
                val db = DbHelper(this, null)
                val isAuth = db.getUser(login, pass)

                if (isAuth) {
                    currentUser = login
                    Toast.makeText(this, "User logged: $login", Toast.LENGTH_LONG).show()
                    userLogin.text.clear()
                    userPass.text.clear()

                    val intent = Intent(this, CoffeesActivity::class.java)
                    startActivity(intent)
                }
                else {
                    Toast.makeText(this, "Login or Password is incorrect", Toast.LENGTH_LONG).show()
                }
            }
        }

        linkToReg.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}