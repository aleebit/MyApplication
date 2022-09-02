package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //connect activity class with recyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // vertical view
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        //Create a bridge between api client and api interface
        val apiService =
            ApiClient.getClient()?.create<ApiInterface>(ApiInterface::class.java)
        val call: Call<List<JsonDataClass>>? = apiService?.getJsonData()

        //help to create separate trail help to retrive a server
        call?.enqueue(object : Callback<List<JsonDataClass>> {
            override fun onResponse(
                call: Call<List<JsonDataClass>>,
                response: Response<List<JsonDataClass>>
            ) {

                //received a JSON data list
                val jsonDataList = response.body()

                //modify the data
                val mainList = ArrayList<JsonDataClass>()
                if (jsonDataList != null) {
                    val hashMap = jsonDataList
                        .filter { !it.name.isNullOrEmpty() }
                        .groupBy {
                            it.listId
                        }.toSortedMap(compareBy { it })
                    hashMap.values.forEach{
                        val sortedList = it.sortedBy { jsonData -> jsonData.name }
                        mainList.addAll(sortedList)
                    }
                    // Single text use multiple time
                    val adapterClass = AdapterClass(mainList)
                    recyclerView.adapter = adapterClass
                }
            }

            override fun onFailure(call: Call<List<JsonDataClass>>, t: Throwable) {
                Log.e(MainActivity::class.java.simpleName, "Fail to load api " + t.message)
            }


        })





    }
}