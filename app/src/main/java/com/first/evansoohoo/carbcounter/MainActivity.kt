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
import android.content.Context

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

        @Insert()
        fun insertUser(user: User )

        @Query("SELECT * FROM user")
        fun getAll(): List<User>

        @Query("SELECT * FROM user WHERE uid IN (:userIds)")
        fun loadAllByIds(userIds: IntArray): List<User>

        @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1")
        fun findByName(first: String, last: String): User

        //@Insert
        //fun insertAll(vararg users: User)

        @Delete
        fun delete(user: User)
    }

    @Database(entities = arrayOf(User::class), version = 1)
    abstract class AppDatabase : RoomDatabase() {

        abstract fun userDao(): UserDao

        companion object {

            /**
             * The only instance
             */
            private var sInstance: AppDatabase? = null

            /**
             * Gets the singleton instance of SampleDatabase.
             *
             * @param context The context.
             * @return The singleton instance of SampleDatabase.
             */
            @Synchronized
            fun getInstance(context: Context): AppDatabase {
                if (sInstance == null) {
                    sInstance = Room
                            .databaseBuilder(context.applicationContext, AppDatabase::class.java, "example")
                            .fallbackToDestructiveMigration()
                            .build()
                }
                return sInstance!!
            }
        }
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

            //var thisUserDao: UserDao? = null
            //val usersDao = db.getDatabase(application).wordDao()

            /*
            ES: This is how the coder in android-room-example does it
            mAdapter = BillAdapter(this@BillsActivity, mutableListOf())
            bills_list.adapter = mAdapter

            doAsync {

                val database = AppDatabase.getInstance(context = this@BillsActivity)
                val bills = database.billDao().all

                uiThread {
                   mAdapter!!.addAll(bills)
                }
            }

            Unrelated, but this is how they do an insertion
            private fun saveAmount(amount: String, customer: Customer) {
                doAsync {
                    val bill = Bill(amount = Integer.valueOf(amount), customerId = customer.uid)
                    AppDatabase.getInstance(this@MainActivity).billDao().insert(bill)
                }
           }
            */

            Log.d("TAG", "ES: Defined user. using separate thread")
            var thisUserDao = db?.userDao()
            doAsync {
                Log.d("TAG", "ES: ENTERED DOASYNC LOOP I WANT TO SEE THIS JFC")
                with(thisUserDao) {
                    var user1 = User(uid=0,firstName="Evan", lastName="SooHoo")
                    var user2 = User(uid=1, firstName="dj", lastName="Trump")
                    Log.d("TAG", "ES: I ALSO WANT TO SEE THIS TO INSERT ENTRY JFK (attempting insertUser below)")
                    //thisUserDao?.insertUser(user2) //Temporarily trying the room example approach
                    AppDatabase.getInstance(this@MainActivity).userDao().insertUser(user2)

                    Log.d("TAG", "ES: Test to see if it ever gets past insertUser") //it never gets here
                    //it never gets here
                    Log.d("TAG", "ES: Just inserted entry") //actually, it does insert an entry the first time
                    //this?.findByName("Evan","SooHoo")

                    /*
                    uiThread {
                        val allUserData = thisUserDao?.getAll()
                        val entries = allUserData.size
                        Log.d("TAG", "ES: Now the number of entries is %entries")
                    }
                    */
                }
            }

        }
    }

}
