package com.example.mad_practical11_21012021060

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fetchBtn : FloatingActionButton = findViewById(R.id.fetchButton)
        recyclerView = findViewById(R.id.recyclerView)

        fetchBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val data = HttpRequest().makeServiceCall(
                        "https://api.json-generator.com/templates/qjeKFdjkXCdK/data",
                        "rbn0rerl1k0d3mcwgw7dva2xuwk780z1hxvyvrb1"
                    )
                    withContext(Dispatchers.Main) {
                        try {
                            if(data != null)
                            {
                                runOnUiThread{getPersonDetailsFromJson(data)}
                            }
                        }
                        catch (e: Exception)
                        {
                            e.printStackTrace()
                        }
                    }
                }
                catch (e: Exception)
                {
                    e.printStackTrace()
                }
            }
        }
    }



    private fun getPersonDetailsFromJson(sJson: String?)
    {
        val personList = ArrayList<Person>()
        try {
            val jsonArray = JSONArray(sJson)
            for(i in 0 until jsonArray.length())
            {
                val jsonObject = jsonArray[i] as JSONObject
                val person = Person(jsonObject)
                personList.add(person)
            }
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter =PersonAdapter(this, personList)
        }
        catch (e: JSONException)
        {
            e.printStackTrace()
        }
    }
    }
