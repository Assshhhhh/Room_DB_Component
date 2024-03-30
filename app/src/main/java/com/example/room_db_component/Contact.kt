package com.example.room_db_component

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity (tableName = "Contact")
data class Contact(

    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val phone: String,
    val creationDate: Date,
    val isActive: Int

)
