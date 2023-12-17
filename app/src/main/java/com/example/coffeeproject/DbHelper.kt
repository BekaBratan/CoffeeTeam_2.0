package com.example.coffeeproject

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.EditText

class DbHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, "app", factory, 1) {

    private lateinit var currentUser: String
    private lateinit var coffees: List<Coffee>

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE users (id INT PRIMARY KEY, login TEXT, email TEXT, pass TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val queryDrop = "DROP TABLE IF EXISTS users"
        db!!.execSQL(queryDrop)
        onCreate(db)
    }

    fun addUser(user: User) {
        val values = ContentValues()
        values.put("login", user.login)
        values.put("email", user.email)
        values.put("pass", user.pass)

        val db = this.writableDatabase
        db.insert("users", null, values)

        val query = "CREATE TABLE cart (id INT PRIMARY KEY, user_login INT, coffee_id INT)"
        db!!.execSQL(query)

        db.close()
    }

    fun addCart(coffee: Coffee){
        val values = ContentValues()
        values.put("user_login", currentUser)
        values.put("coffee_id", coffee.id)

        val db = this.writableDatabase
        db.insert("cart", null, values)

        db.close()
    }

    fun removeCart(coffee: Coffee){
        val db = this.writableDatabase
        val coffeeId = coffee.id
        val query = "DELETE FROM cart WHERE Id = (SELECT MIN(id) FROM cart WHERE user_login = '$currentUser' AND coffee_id = '$coffeeId')"
        db!!.execSQL(query)

        db.close()
    }

    fun getCart(): Map<Int, Int>? {
        val db = this.readableDatabase
        val cartMap = mutableMapOf<Int, Int>()
        var cursor: Cursor

        for (i in 1..coffees.size+1) {
            cursor = db.rawQuery("SELECT * FROM cart WHERE user_login = '$currentUser' AND coffee_id = '$i'", null)
            cartMap[i] = cursor.count
        }
        return cartMap
    }

    fun getUser(login: String, pass: String): Boolean{
        val db = this.readableDatabase

        val querySelect = "SELECT * FROM users WHERE login = '$login' AND pass = '$pass'"
        val result = db.rawQuery(querySelect, null)

        return result.moveToFirst()
    }

    fun setUser(login: String){
        currentUser = login
    }

    fun getUser(): String{
        return currentUser
    }
    fun setCoffees(coffeeList: List<Coffee>){
        coffees = coffeeList
    }

    fun getCoffees(): List<Coffee>{
        return coffees
    }
}