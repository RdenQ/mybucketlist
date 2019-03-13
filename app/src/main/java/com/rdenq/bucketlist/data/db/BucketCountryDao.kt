package com.rdenq.bucketlist.data.db

import androidx.room.*
import com.rdenq.bucketlist.data.model.BucketCountry
import com.rdenq.bucketlist.data.model.CountryAndBucketCountries

@Dao
interface BucketCountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBucketCountry(bucketCountry: BucketCountry): Long

    @Delete
    fun deleteBucketCountry(bucketCountry: BucketCountry): Int

    @Query("SELECT * from BucketCountry")
    fun selectAllBucketCountries(): MutableList<BucketCountry>

    @Query("SELECT * FROM BucketCountry WHERE bcountry_name = :bcountryName")
    fun getBucketCountryForCountry(bcountryName: String): BucketCountry

    @Query("SELECT * FROM BucketCountry WHERE id = :bucketCountryId")
    fun getBucketCountry(bucketCountryId: Long): BucketCountry

    /**
     * This query will tell Room to query both the [Country] and [BucketCountry] tables and handle
     * the object mapping.
     */
    @Transaction
    @Query("SELECT * FROM Country")
    fun getCountryAndBucketCountries(): MutableList<CountryAndBucketCountries>

}