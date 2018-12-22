package com.first.evansoohoo.carbcounter

import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    //var sum:  Int = 0

    @Entity
    data class User(
            @PrimaryKey var uid: Int,
            @ColumnInfo(name = "first_name") var firstName: String?,
            @ColumnInfo(name = "last_name") var lastName: String?
    )

    @Dao
    interface UserDao {
        @Query("SELECT * FROM user")
        fun getAll(): List<User>

        @Query("SELECT * FROM user WHERE uid IN (:userIds)")
        fun loadAllByIds(userIds: IntArray): List<User>

        @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1")
        fun findByName(first: String, last: String): User

        //@Insert
        //fun insertAll(vararg users: User)

        @Insert
        fun insertUser(user: User )

        @Delete
        fun delete(user: User)
    }

    @Database(entities = arrayOf(User::class), version = 1)
    abstract class AppDatabase : RoomDatabase() {
        abstract fun userDao(): UserDao
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        var sum:  Int = 0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            print("blahblahhey this is the print button")
            var inputInt: Int = carbInput.text.toString().toInt()
            sum+= inputInt
            Toast.makeText(this,Integer.toString(sum),Toast.LENGTH_LONG).show()
            val db = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "database-name"
            ).build()

            //MyApp.database =  Room.databaseBuilder(this, AppDatabase::class.java, "db").allowMainThreadQueries().build()



            var thisUserDao: UserDao? = null
            //val usersDao = db.getDatabase(application).wordDao()
            var User1 = User(uid=0,firstName="Evan", lastName="SooHoo")
            Log.d("TAG", "ES: Defined user. using separate thread")
            thisUserDao = db?.userDao()
            doAsync {
                Log.d("TAG", "ES: ENTERED DOASYNC LOOP I WANT TO SEE THIS JFC")
                with(thisUserDao) {//THIS is the part that we're not entering
                    this?.insertUser(User1)
                    Log.d("TAG", "ES: Just inserted entry")
                    //this?.findByName("Evan","SooHoo")
                    uiThread {
                        val allUserData = thisUserDao?.getAll()
                        val entries = allUserData.size
                        Log.d("TAG", "ES: Now the number of entries is %entries")
                    }
                }
            }



        }
    }

}
