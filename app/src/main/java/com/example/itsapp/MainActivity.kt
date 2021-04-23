package com.example.itsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val url = "http://ec2-13-209-75-229.ap-northeast-2.compute.amazonaws.com:3000"

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val server = retrofit.create(APIInterface::class.java)


        join_btn.setOnClickListener{
            val intent = Intent(this, JoinActivity::class.java)
            startActivity(intent)
        }

        get_btn.setOnClickListener {
            server.getRequest("name").enqueue(object:Callback<ResponseDC>{
                override fun onFailure(call: Call<ResponseDC>, t: Throwable) {
                }

                override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                    Log.d("response : ", response?.body().toString())
                }

            })
        }

        post_btn.setOnClickListener {
            server.postRequest("id", "password").enqueue(object:Callback<ResponseDC>{
                override fun onFailure(call: Call<ResponseDC>, t: Throwable) {
                }

                override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                    Log.d("response : ", response?.body().toString())
                }

            })
        }

        update_btn.setOnClickListener {
            server.putRequest("board","내용").enqueue(object:Callback<ResponseDC>{
                override fun onFailure(call: Call<ResponseDC>, t: Throwable) {
                }

                override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                    Log.d("response : ", response?.body().toString())
                }

            })
        }

        delete_btn.setOnClickListener {
            server.deleteRequest("board").enqueue(object:Callback<ResponseDC>{
                override fun onFailure(call: Call<ResponseDC>, t: Throwable) {
                }

                override fun onResponse(call: Call<ResponseDC>, response: Response<ResponseDC>) {
                    Log.d("response : ", response?.body().toString())
                }
            })
        }
    }
}