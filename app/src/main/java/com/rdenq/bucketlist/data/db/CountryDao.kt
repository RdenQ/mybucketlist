package com.rdenq.bucketlist.data.db

import androidx.room.*
import com.rdenq.bucketlist.data.model.Country

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountry(country: Country): Long

    @Delete
    fun deleteCountry(country: Country): Int

    @Query("SELECT * from Country")
    fun selectAllCountries(): MutableList<Country>

    @Query("SELECT * FROM Country WHERE name = :countryName")
    fun getCountry(countryName: String): Country

}