package com.rdenq.bucketlist.data.repository

import com.rdenq.bucketlist.data.model.Country
import com.rdenq.bucketlist.data.network.ApiError
import io.reactivex.disposables.Disposable

interface AppRepository {

    fun getCountries(
        success: (MutableList<Country>) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}
    ): Disposable

    fun getCountry(
        countryName: String,
        success: (Country) -> Unit,
        failure: (ApiError) -> Unit = {},
        terminate: () -> Unit = {}
    ): Disposable

    fun insertCountry(country: Country): Disposable
}