package com.examples.medicinetracker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(UserInformation::class,MedicineInformation::class), version = 2, exportSchema = false)
public abstract class AppInformationDatabase : RoomDatabase() {

    abstract fun UserInformationDao(): UserInformationDao
    abstract fun MedicineInformationDao():MedicineInformationDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppInformationDatabase? = null

        fun getDatabase(context: Context): AppInformationDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppInformationDatabase::class.java,
                    "name_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}