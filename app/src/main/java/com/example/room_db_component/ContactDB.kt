package com.example.room_db_component

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope

@Database(entities = [Contact::class], version = 2 )
@TypeConverters(Converters::class)
abstract class ContactDB : RoomDatabase() {

    abstract fun contactDao() : ContactDAO

    // Singleton
    companion object{

        val migration_1_2 = object: Migration(1, 2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE Contact ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
            }

        }

        @Volatile
        private var INSTANCE: ContactDB? = null

        fun getDatabase(context: Context) : ContactDB{

            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDB::class.java,
                        "ContactDB"
                        )
                        .addMigrations(migration_1_2)
                        .build()
                }
            }
            return INSTANCE!!

        }

    }

}