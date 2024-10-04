package com.example.booking_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        find ids
        var create=findViewById<CardView>(R.id.create)
//        give it functionaliity
        //what happens when someone clicks on the card?
//        setonclicklistener display a toast message
        create.setOnClickListener {
            Toast.makeText(applicationContext,
                "Clicked on Create card", Toast.LENGTH_SHORT).show()
        } //end of setonclicklistener
        var home=findViewById<CardView>(R.id.home)
        //go to another activity
        //from main activity to the GetRooms activity
        home.setOnClickListener {
            //intent
            var x=Intent(applicationContext,GetRoomsActivity::class.java)
            startActivity(x)
        }

    }
}
//postman
//home
//profile+
//create
//search
//settings
//toggle button