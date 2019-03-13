package com.rdenq.bucketlist.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rdenq.bucketlist.data.model.BucketCountry
import com.rdenq.bucketlist.data.model.Country

@Database(entities = [Country::class, BucketCountry::class], version = AppDatabase.VERSION)
@TypeConverters(CustomConverters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "countries.db"
        const val VERSION = 2
    }

    abstract fun countryDao(): CountryDao
    abstract fun bucketCountryDao(): BucketCountryDao
}