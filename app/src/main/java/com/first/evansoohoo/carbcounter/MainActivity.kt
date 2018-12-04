package com.first.evansoohoo.carbcounter

import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    //var sum:  Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        var sum:  Int = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            println("HEY WE ENTERED THE STATEMENT")
            var inputInt: Int = carbInput.text.toString().toInt() //this line is wrong
            sum+= inputInt //WAIT IT'S ACTUALLY THESE THAT MESS UP
            println("Now the value of sum is $sum")
            //Toast.makeText(this,sum,Toast.LENGTH_LONG).show()

        }


    }
}
