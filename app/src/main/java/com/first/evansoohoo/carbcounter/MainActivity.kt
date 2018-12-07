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

        }
    }

    @Entity(tableName = "list_categories")
    data class ListCategory(@ColumnInfo(name="category_name") var categoryName: String,
                            @ColumnInfo(name="id") @PrimaryKey(autoGenerate = true) var id: Long = 0)

}
