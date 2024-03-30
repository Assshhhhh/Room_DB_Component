package com.example.room_db_component

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var database: ContactDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        database = ContactDB.getDatabase(this)

        // If not using Singleton Method
        /*database = Room.databaseBuilder(
            applicationContext,
            ContactDB::class.java,
            "ContactDB"
        ).build()*/

        GlobalScope.launch {
            database.contactDao().insertContact(Contact(0, "Room User 1", "0123456789", Date(), 1))
        }

    }

    fun getContact(view: View) {
        database.contactDao().getContact().observe(this, Observer {
            Log.d("Room", it.toString())
        })
    }

}