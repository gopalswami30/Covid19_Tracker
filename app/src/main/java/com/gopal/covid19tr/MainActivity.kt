package com.gopal.covid19tr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
        lateinit var StateAdapter:stateAdapter
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            list.addHeaderView(LayoutInflater.from(this).inflate(R.layout.item_header,list,false))
            fetchResult()
        }

        private fun fetchResult() {
            GlobalScope.launch {
                val response= withContext(Dispatchers.IO){Client.api.execute()}
                if(response.isSuccessful){
                    val data= Gson().fromJson(response.body?.string(),Response::class.java)
                    launch(Dispatchers.Main){
                        bindCombineData(data.statewise.get(0))
                        bindStateWiseData(data.statewise.subList(0,data.statewise.size))
                    }
                }
            }

        }

        private fun bindStateWiseData(subList: List<statewiseItem?>) {
            StateAdapter= stateAdapter(subList as List<statewiseItem>)
            list.adapter=StateAdapter
        }

        private fun bindCombineData(data: statewiseItem?) {
            //val lastUpdatedTIme:String?=data?.lastupdatedtime
            //val simpleDateFormat= SimpleDateFormat("dd/mm/yyyy hh:mm:ss")
            lastUpdatedTv.text="Last Updated\n ${data?.lastupdatedtime}"//getTimeAgo(simpleDateFormat.parse(lastUpdatedTIme))}"
            confirmedTv.text=data?.confirmed
            recoveredTv.text=data?.recovered
            activeTv.text=data?.active
            deceasedTv.text=data?.deaths
        }
       /* fun getTimeAgo(past: Date):String{
            val now= Date()
            val sec= TimeUnit.MILLISECONDS.toSeconds(now.time-past.time)
            val min= TimeUnit.MILLISECONDS.toMinutes(now.time-past.time)
            val hr= TimeUnit.MILLISECONDS.toHours(now.time-past.time)
            return when{
                (sec<60)->{
                    "$sec seconds ago"
                }
                (min<60)->{
                    "$min minutes ago"
                }
                (hr<24)->{
                    "$hr hour ${min%60} min ${sec%3600} ago"
                }
                else->{
                    SimpleDateFormat("dd/mm/yyyy hh:mm:ss").format(past).toString()
                }
            }
        }*/
    }

