package com.example.coffeeproject

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, "app", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val queryUsers = "CREATE TABLE users (id INT PRIMARY KEY, login TEXT, email TEXT, pass TEXT)"
        db!!.execSQL(queryUsers)

        val queryCart = "CREATE TABLE cart (id INT PRIMARY KEY, user_login INT, coffee_id INT, quantity INT)"
        db!!.execSQL(queryCart)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        val queryDropUsers = "DROP TABLE IF EXISTS users"
        val queryDropCart = "DROP TABLE IF EXISTS cart"
        db!!.execSQL(queryDropUsers)
        db!!.execSQL(queryDropCart)
        onCreate(db)
    }

    fun addUser(user: User) {
        val values = ContentValues()
        values.put("login", user.login)
        values.put("email", user.email)
        values.put("pass", user.pass)

        val db = this.writableDatabase
        db.insert("users", null, values)

        db.close()
    }

    fun addCart(coffee: Coffee){
        val values = ContentValues()
        values.put("user_login", currentUser)
        values.put("coffee_id", coffee.id)
        var cursor: Cursor?

        val db = this.writableDatabase

        var count = 0
        val query = "SELECT quantity FROM cart WHERE (user_login = ?) AND (coffee_id = ?)"
        cursor = db.rawQuery(query, arrayOf(currentUser, coffee.id.toString()))
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0)
        }

        if (count==0) {
            values.put("quantity", 1)
            db.insert("cart", null, values)
            Log.d("TAG", "addCart: added")
        } else {
            values.put("quantity", count + 1)
            db.update("cart", values, "(user_login = ?) AND (coffee_id = ?)", arrayOf(currentUser, coffee.id.toString()))
            Log.d("TAG", "addCart: not added and $count")
        }
        cursor?.close()
        db.close()
    }

    fun removeCart(coffee: Coffee){
        val values = ContentValues()
        values.put("user_login", currentUser)
        values.put("coffee_id", coffee.id)

        val db = this.writableDatabase
        val coffeeId = coffee.id
        val cursor: Cursor

//        var quantity = 0
//        cursor = db?.rawQuery("SELECT quantity FROM example_table WHERE product_id = ?", arrayOf(coffeeId.toString()))
//        quantity = cursor?.getInt(cursor.getColumnIndexOrThrow("quantity")) ?: 0
//        Log.d("TAG", "removeCart: $quantity")

        var count = 0
        val query = "SELECT quantity FROM cart WHERE (user_login = ?) AND (coffee_id = ?)"
        cursor = db.rawQuery(query, arrayOf(currentUser, coffeeId.toString()))
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0)
        }

        if (count > 1) {
            values.put("quantity", count - 1)
            db.update("cart", values, "(user_login = ?) AND (coffee_id = ?)", arrayOf(currentUser, coffee.id.toString()))
            Log.d("TAG", "addCart: not added and $count")
        } else {
            db.delete("cart","(user_login = ?) AND (coffee_id = ?)", arrayOf(currentUser, coffee.id.toString()))
            Log.d("YourTag", "CurrentUser: $currentUser, CoffeeID: $coffeeId")
        }
//        db!!.delete("cart", "id IN SELECT id FROM cart WHERE user_login=? AND coffee_id=? ORDER BY coffee_id LIMIT 1)", arrayOf(currentUser, coffeeId.toString()))
        cursor?.close()
        db?.close()
    }

    fun getCart(): Map<Int, Int>? {
        val db = this.readableDatabase
        val cartMap = mutableMapOf<Int, Int>()
        var cursor: Cursor

        for (i in 0..<coffees.size) {
            var count = 0
            val query = "SELECT quantity FROM cart WHERE (user_login = ?) AND (coffee_id = ?)"
            cursor = db.rawQuery(query, arrayOf(currentUser, i.toString()))
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0)
            }
            cursor.close()
            cartMap[i] = count
            Log.d("YourTag", "CurrentUser: $currentUser, CoffeeID: $i")
            Log.i("$i", "getCart: $count")
        }

        db.close()
        return cartMap
    }

    fun getUser(login: String, pass: String): Boolean{
        val db = this.readableDatabase
        val querySelect = "SELECT * FROM users WHERE login = '$login' AND pass = '$pass'"
        val result = db.rawQuery(querySelect, null)

        return result.moveToFirst()
    }
}