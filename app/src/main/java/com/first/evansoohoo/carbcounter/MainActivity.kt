package com.first.evansoohoo.carbcounter

import com.first.evansoohoo.carbcounter.FeedReaderContract
import com.first.evansoohoo.carbcounter.FeedReaderDbHelper

import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.database.sqlite.*

import android.content.Context


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
            var context: Context? = null
            val dbHelper = FeedReaderDbHelper(context)

            // Gets the data repository in write mode
            val db = dbHelper.writableDatabase

            // Create a new map of values, where column names are the keys
            val values = ContentValues().apply {
                put(FeedEntry.COLUMN_NAME_TITLE, title)
                put(FeedEntry.COLUMN_NAME_SUBTITLE, subtitle)
            }

            // Insert the new row, returning the primary key value of the new row
            val newRowId = db?.insert(FeedEntry.TABLE_NAME, null, values)

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            val projection = arrayOf(BaseColumns._ID, FeedEntry.COLUMN_NAME_TITLE, FeedEntry.COLUMN_NAME_SUBTITLE)

            // Filter results WHERE "title" = 'My Title'
            val selection = "${FeedEntry.COLUMN_NAME_TITLE} = ?"
            val selectionArgs = arrayOf("My Title")

            // How you want the results sorted in the resulting Cursor
            val sortOrder = "${FeedEntry.COLUMN_NAME_SUBTITLE} DESC"

            val cursor = db.query(
                    FeedEntry.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    sortOrder               // The sort order
            )


        }
    }

}
