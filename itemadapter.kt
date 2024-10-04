package com.example.booking_app


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.opengl.Visibility
import android.view.Display.Mode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class ItemAdapter(val context:Context) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){
    //define the list
    var itemlist:List<Item> = listOf() //empty list

    inner class ItemViewHolder(itemView:View)
        :RecyclerView.ViewHolder(itemView){
        //find the views(ids)
        var image_url=itemView.findViewById<ImageView>(R.id.image_url)
        val room_name=itemView.findViewById<TextView>(R.id.room_name)
        val cost=itemView.findViewById<TextView>(R.id.cost)
        val availability=itemView.findViewById<TextView>(R.id.availability)
        //create  oncreateViewholder function(holds the single item views)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        //define the layout
        //layout inflater
        val itemView=LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item,parent,false)
        return ItemViewHolder(itemView)
    }
    //create onBindViewHolder
    override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
        val currentitem=itemlist[position]
        val image=holder.itemView.findViewById<ImageView>(R.id.image_url)
        val room_name=holder.itemView.findViewById<TextView>(R.id.room_name)
        val cost=holder.itemView.findViewById<TextView>(R.id.cost)
        val availability=holder.itemView.findViewById<TextView>(R.id.availability)

        val desc=holder.itemView.findViewById<TextView>(R.id.room_desc)
        desc.visibility=View.GONE
        val id=holder.itemView.findViewById<TextView>(R.id.room_id)
        id.visibility=View.GONE
        val num=holder.itemView.findViewById<TextView>(R.id.square_feet)
        desc.visibility=View.GONE
//        /convert data

        val item=itemlist[position]
        room_name.text=item.room_name
        cost.text=item.cost
        availability.text=item.availability


        Glide.with(context).load(item.image_url)
            .apply(RequestOptions().centerCrop()).into(image)


        holder.itemView.setOnClickListener {
            desc.text=item.room_desc
            desc.visibility=View.GONE
            id.text=item.room_desc
            id.visibility=View.GONE
            num.text=item.square_feet
            num.visibility=View.GONE
            //create the shared preference variavle
            val sharedpref:SharedPreferences=context.getSharedPreferences(
                "file",Context.MODE_PRIVATE)//only accessible in this app
            //define a variable to hold and save the conference room data
            val editor:SharedPreferences.Editor=sharedpref.edit()
            editor.putString("img_url",item.image_url)
            editor.putString("room_name",item.room_name)
            editor.putString("availability",item.availability)
            editor.putString("cost",item.cost)
            editor.putInt("room_id",item.room_id)
            editor.putString("square_feet",item.square_feet)
            editor.putString("room_desc",item.room_desc)

            editor.apply()
            val x=Intent(context, SingleActivity::class.java)
            x.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(x)
        }
    }
    override fun getItemCount(): Int {
        return itemlist.size
    }
    //function to set the conference room list
    fun setConferenceItems(itemlist:List<Item>){
        this.itemlist=itemlist
        notifyDataSetChanged()
    }
}