package com.sky.data

import android.arch.persistence.room.Database
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sky.model.PhoneNumberDTO
import com.sky.model.PhoneNumberDao

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(PhoneNumberDTO::class), version = 1, exportSchema = false)
public abstract class PhoneNumberRoomDatabase : RoomDatabase() {

    abstract fun phoneNumberDao(): PhoneNumberDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: PhoneNumberRoomDatabase? = null

        fun getDatabase(context: Context): PhoneNumberRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PhoneNumberRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}