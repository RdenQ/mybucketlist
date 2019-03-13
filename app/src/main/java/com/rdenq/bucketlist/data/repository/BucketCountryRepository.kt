package com.rdenq.bucketlist.data.repository

import com.rdenq.bucketlist.data.model.BucketCountry
import com.rdenq.bucketlist.data.model.CountryAndBucketCountries
import com.rdenq.bucketlist.data.network.ApiError
import io.reactivex.disposables.Disposable

interface BucketCountryRepository {

    fun getBucketCountries(
        success: (MutableList<BucketCountry>) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}
    ): Disposable

    fun getCountryAndBucketCountries(
        success: (MutableList<CountryAndBucketCountries>) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}
    ): Disposable

    fun getBucketCountryForCountry(
        bcountryName: String,
        success: (BucketCountry) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}
    ): Disposable

    fun getBucketCountry(
        bucketCountryId: Long,
        success: (BucketCountry) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}
    ): Disposable

    fun insertBucketCountry(countryName: String): Disposable

}