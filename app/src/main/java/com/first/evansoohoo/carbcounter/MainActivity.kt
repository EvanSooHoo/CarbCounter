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
            var inputInt: Int = carbInput.text.toString().toInt()
            sum+= inputInt
            Toast.makeText(this,Integer.toString(sum),Toast.LENGTH_LONG).show()

            @Entity
            data class User(
                    @PrimaryKey var uid: Int,
                    @ColumnInfo(name = "first_name") var firstName: String?,
                    @ColumnInfo(name = "last_name") var lastName: String?
            )
        }
    }

    //@Entity(tableName = "list_categories") //These three lines of code are the problem
    //data class ListCategory(@ColumnInfo(name="category_name") var categoryName: String,
     //                       @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Long = 0)

    /*
    @Entity(tableName = "all_food_list")
    class Food (@ColumnInfo(name = "food_name") var foodName: String = "",
                @ColumnInfo(name = "food_desc") var foodDesc: String = "",
                @ColumnInfo(name = "protein") var protein: Double = 0.0,
                @ColumnInfo(name = "carbs") var carbs: Double = 0.0,
                @ColumnInfo(name = "fat") var fat: Double = 0.0,
                @ColumnInfo(name = "calories") var calories: Double = 0.0)
    {
        @ColumnInfo(name = "id")
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0
    }
    */


}
