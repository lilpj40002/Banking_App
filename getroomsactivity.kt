package com.example.booking_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray

class GetRoomsActivity : AppCompatActivity() {
    //initialize the itemAdapter
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_rooms)
//        find the recyclerview
        val recyclerView:RecyclerView=findViewById<RecyclerView>(R.id.recyclerview)
        val progressbar=findViewById<ProgressBar>(R.id.progressbar)
        adapter= ItemAdapter(applicationContext)
        recyclerView.layoutManager=LinearLayoutManager(applicationContext)
        recyclerView.setHasFixedSize(true)

//        define the api to fetch our rooms
        //loopj to consume the api
        val client=AsyncHttpClient(80,443)
//        define the http response method to use
        client.get(this,
            "https://maxmusau.pythonanywhere.com/getrooms",
            null,"application/json",
            object :JsonHttpResponseHandler(){
//                onsuccess
                override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    response: JSONArray?
                ) {
                    //convert the jsonarray into a list
                    val gson=GsonBuilder().create()
                    //create the list
                    val list=gson.fromJson(response.toString(),Array<Item>::class.java).toList()
                    //pass the converted list into the adapater
                    adapter.setConferenceItems(list)
                        progressbar.visibility=View.GONE
                }//end of onsuccess
                //onfailure
                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseString: String?,
                    throwable: Throwable?
                ) {
                    progressbar.visibility=View.GONE
                    Toast.makeText(applicationContext,
                        "Something Went wrong", Toast.LENGTH_SHORT).show()

                }
            }
            )//end of client get method
        //put the adpater into the recycler view
        recyclerView.adapter=adapter
    }
}