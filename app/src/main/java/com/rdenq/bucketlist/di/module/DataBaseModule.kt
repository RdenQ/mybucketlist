package com.rdenq.bucketlist.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import com.rdenq.bucketlist.data.db.AppDatabase
import com.rdenq.bucketlist.data.db.BucketCountryDao
import com.rdenq.bucketlist.data.db.CountryDao
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideCountryDao(appDataBase: AppDatabase): CountryDao {
        return appDataBase.countryDao()
    }

    @Provides
    fun provideBucketCountryDao(appDataBase: AppDatabase): BucketCountryDao {
        return appDataBase.bucketCountryDao()
    }
}