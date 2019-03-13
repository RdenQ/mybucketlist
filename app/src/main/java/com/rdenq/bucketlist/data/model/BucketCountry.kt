package com.rdenq.bucketlist.data.model

import androidx.room.*
import java.util.*

@Entity(
    tableName = "BucketCountry",
    foreignKeys = [ForeignKey(entity = Country::class, parentColumns = ["name"], childColumns = ["bcountry_name"])],
    indices = [Index("bcountry_name")]
)
data class BucketCountry(
    @ColumnInfo(name = "bcountry_name") val bcountryName: String,
    @ColumnInfo(name = "bcountry_date") val bcountryDate: Calendar = Calendar.getInstance()

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var bucketCountryId: Long = 0
}