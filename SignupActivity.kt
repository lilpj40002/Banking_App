package com.example.booking_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import org.json.JSONObject

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        var username=findViewById<EditText>(R.id.username)
        var email =findViewById<EditText>(R.id.email)
        var phone =findViewById<EditText>(R.id.phone)
        var password =findViewById<EditText>(R.id.password)
        var confirm_password =findViewById<EditText>(R.id.confirm_password)

//        prgressbar
        var progressbar =findViewById<ProgressBar>(R.id.progressbar)
        progressbar.visibility=View.GONE
//        signup button
        var signup =findViewById<Button>(R.id.signup)
        signup.setOnClickListener {
//            implement the signup api here
            progressbar.visibility=View.VISIBLE
//            we are going to use the loopj library to consume our api(make async requests)
            //http(s) requests
            //create the http client
            var client=AsyncHttpClient(80,443)
//          create a body to hold the user info
            var body =JSONObject()
//            PUT the data into the jsonobject body
            body.put("username",username.text.toString())
            body.put("email",email.text.toString())
            body.put("phone",phone.text.toString())
            body.put("password",password.text.toString())
            body.put("confirm_password",confirm_password.text.toString())
//justpaste.it/ctw73
            val con_body= StringEntity(body.toString())
//            define the http method to use
            client.post(this,
                "https://maxmusau.pythonanywhere.com/signup",
                con_body,"application/json",
                object : JsonHttpResponseHandler() {
                    //onsuccess function
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        response: JSONObject?
                    ) {
//                    check if the status code is 200
                        if (statusCode == 200){
                            Toast.makeText(applicationContext, "Signup Successful"+statusCode,
                                Toast.LENGTH_SHORT).show()
//                        intent to the signin activity
                            val x=Intent(applicationContext,SigninActivity::class.java)
                            startActivity(x)
                        }//end of if
                        else{
                            progressbar.visibility=View.GONE
                            Toast.makeText(applicationContext,
                                "Failed. Please try again"+statusCode,
                                Toast.LENGTH_SHORT).show()
                        }
                    } //end of onsuccess

                    override fun onFailure(
                        statusCode: Int,
                        headers: Array<out Header>?,
                        throwable: Throwable?,
                        errorResponse: JSONObject?
                    ) {
                        //hide the progressbar
                        progressbar.visibility=View.GONE
                        Toast.makeText(applicationContext,
                            "Something went Wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }

        var signin=findViewById<TextView>(R.id.login)
        signin.setOnClickListener {
            var x=Intent(applicationContext,SigninActivity::class.java)
            startActivity(x)
        }

//create a dabasabe called booking_db

    }
}