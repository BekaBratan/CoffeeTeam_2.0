package com.example.coffeeproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class CoffeesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffees)

        val coffeeList: RecyclerView = findViewById(R.id.coffee_list)
        val coffees = arrayListOf<Coffee>()

        coffees.add(Coffee(1, "Americano", "Americano", "Espresso, Hot water", "With a similar flavor to black coffee, the americano consists of an espresso shot diluted in hot water. Pro tip: if you’re making your own, pour the espresso first, then add the hot water.", 4.3, 1090))
        coffees.add(Coffee(2, "Latte", "Latte", "Espresso, Steamed milk", "As the most popular coffee drink out there, the latte is comprised of a shot of espresso and steamed milk with just a touch of foam. It can be ordered plain or with a flavor shot of anything from vanilla to pumpkin spice. (Here’s how to make a copycat Starbucks pumpkin spice latte.)", 4.8, 1290))
        coffees.add(Coffee(3, "Espresso", "Espresso", "1oz Espresso", "An espresso shot can be served solo or used as the foundation of most coffee drinks, like lattes and macchiatos. If you’re an at-home barista, this Breville Bambino and espresso accessories will turn your house into your own coffee shop. Figure out how an espresso is different from coffee.", 3.8, 890))
        coffees.add(Coffee(4, "Mocha", "Mocha", "Espresso, Chocolate, Steamed milk", "For all you chocolate lovers out there, you’ll fall in love with a mocha (or maybe you already have). The mocha is a chocolate espresso drink with steamed milk and foam.", 4.7, 1890))
        coffees.add(Coffee(5, "Cappuccino", "Cappuccino", "Espresso, Steamed milk, Foam", "Cappuccino is a latte made with more foam than steamed milk, often with a sprinkle of cocoa powder or cinnamon on top. Sometimes you can find variations that use cream instead of milk or ones that throw in flavor shot, as well.", 4.2, 1190))
    }
}