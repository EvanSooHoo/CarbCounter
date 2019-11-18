package com.first.evansoohoo.carbcounter

import kotlinx.android.synthetic.main.activity_main.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.database.sqlite.*

import android.content.Context
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var sum:  Int = 0
        val db = Room.databaseBuilder(
                applicationContext,
                CarbRoomDatabase::class.java, "CarbEntry.db"
        ).build()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            var inputInt: Int = carbInput.text.toString().toInt()
            sum+= inputInt
            Toast.makeText(this,Integer.toString(sum),Toast.LENGTH_LONG).show()
        }

        button2.setOnClickListener {
            Log.d("TAG", "ES: You hit the save button")


            GlobalScope.launch {

                Log.d("TAG", "ES: Inside globalscope launch")
                var carbStringVersion:String = sum.toString()
                carbStringVersion += " recorded on date " + LocalDateTime.now()
                db.CarbEntry().insert(CarbEntry(carbStringVersion)) //TODO: Put back

                //Log.d("TAG", "ES: Now testing the other insert")
                //db.CarbEntry().insertAll(CarbEntry(sum))
                Log.d("TAG", "ES: Now testing getall")
                var data = db.CarbEntry().getAll()

                data?.forEach {
                    println(it.content)
                }

            }
            Log.d("TAG", "ES: Attempted to save sum " + sum + " into database")

            var data = db.CarbEntry().getAllCarbEntries()


            Log.d("TAG", "ES (TEST WITHIN BUTTON 2 METHOD): Size of carb entries database is now " + (data.value?.size))
        }

        button3.setOnClickListener {
            Log.d("TAG", "ES: You hit the previous entries list")
            GlobalScope.launch {
                var data = db.CarbEntry().getAll()

                data?.forEach {
                    println(it.content)
                }
            }
        }


    }

}
