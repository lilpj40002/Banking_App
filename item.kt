package com.example.booking_app

//this is the model
//the model contains dataset
//room_id, room_name,room_desc,num_people,cost,availability
data class Item(
    val room_id:Int,
    val image_url:String,
    val room_name:String,
    val room_desc:String,
    val square_feet:String,
    val cost:String,
    val availability:String
)