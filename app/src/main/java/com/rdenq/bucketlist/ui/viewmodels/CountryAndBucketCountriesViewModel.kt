package com.rdenq.bucketlist.ui.viewmodels

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.rdenq.bucketlist.R
import com.rdenq.bucketlist.data.model.CountryAndBucketCountries
import java.text.SimpleDateFormat
import java.util.*

class CountryAndBucketCountriesViewModel(
    context: Context,
    countries: CountryAndBucketCountries
) : ViewModel() {

    val country = checkNotNull(countries.country)
    private val bucketCountry = countries.bucketListCountries[0]
    private val dateFormat by lazy { SimpleDateFormat("MMM d, yyyy", Locale.US) }
    private val addedDateString by lazy { dateFormat.format(bucketCountry.bcountryDate.time) }

    val addedDate = ObservableField<String>(
        context.getString(
            R.string.bucket_country_added_on, country.name,
            addedDateString
        )
    )
}

