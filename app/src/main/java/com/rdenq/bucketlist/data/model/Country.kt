package com.rdenq.bucketlist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Country")
data class Country(
    @PrimaryKey
    val name: String,
    val alpha2Code: String,
    val alpha3Code: String,
    val nativeName: String,
    val region: String,
    val capital: String,
    val population: Int,
    val lat: Float,
    val lng: Float,
    val flag: String = ""
    //val altSpellings: List<String>,
    //var currencies: List<String>,
    //val borders: List<String>,
    //val languages: List<String>,
    //var translations: List<StringMapEntry>
)
