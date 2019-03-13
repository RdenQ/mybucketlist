package com.rdenq.bucketlist.data.model

import androidx.room.*

/**
 * This class captures the relationship between a [Country] and a user's [BucketCountry], which is
 * used by Room to fetch the related entities.
 */
class CountryAndBucketCountries {

    @Embedded
    lateinit var country: Country

    @Relation(parentColumn = "name", entityColumn = "bcountry_name")
    var bucketListCountries: List<BucketCountry> = arrayListOf()
}
