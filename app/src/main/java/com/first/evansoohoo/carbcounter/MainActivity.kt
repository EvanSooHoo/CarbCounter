package com.first.evansoohoo.carbcounter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    /*
    fun hello()
    {
        val str = "this is in a function!!!"
        print("$str")
    }
    */
    var sum = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        //hello()
        //println("evan wuz hear")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnShow = findViewById<Button>(R.id.button)
        btnShow?.setOnClickListener { Toast.makeText(this@MainActivity, "hey whats up", Toast.LENGTH_LONG).show()}

    }
}
