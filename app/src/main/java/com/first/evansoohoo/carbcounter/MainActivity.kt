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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        var sum:  Int = 0
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            var inputInt: Int = carbInput.text.toString().toInt()
            sum+= inputInt
            Toast.makeText(this,Integer.toString(sum),Toast.LENGTH_LONG).show()
        }

        button2.setOnClickListener {
            Log.d("TAG", "ES: You hit the save button")
            val db = Room.databaseBuilder(
                    applicationContext,
                    CarbRoomDatabase::class.java, "CarbEntry.db"
            ).build()

            GlobalScope.launch {
                db.CarbEntry().insert(CarbEntry(sum))

            }
            Log.d("TAG", "ES: Attempted to save sum " + sum + " into database")
        }

        button3.setOnClickListener {
            Log.d("TAG", "ES: You hit the previous entries list")
            val db = Room.databaseBuilder(
                    applicationContext,
                    CarbRoomDatabase::class.java, "CarbEntry.db"
            ).build()

            var data = db.CarbEntry().getAllCarbEntries()
            Log.d("TAG", "ES: Size of carb entries database is now " + (data.value?.size))


            Log.d("TAG", "ES: Value of saved entry at end is " + (data.getValue()?.get(0)))
        }


    }

}
