package com.rdenq.bucketlist.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rdenq.bucketlist.data.model.Country
import com.rdenq.bucketlist.data.repository.AppRepository
import com.rdenq.bucketlist.data.repository.BucketCountryRepository
import javax.inject.Inject

class CountryDetailsViewModel @Inject constructor(
    appRepository: AppRepository,
    bucketCountryRepository: BucketCountryRepository
) : BaseViewModel() {

    private val TAG = CountryDetailsViewModel::class.java.simpleName
    private var mappRepository: AppRepository
    private var mBucketCountryRepository: BucketCountryRepository
    lateinit var name: String
    val country: MutableLiveData<Country> by lazy { MutableLiveData<Country>() }

    init {
        mappRepository = appRepository
        mBucketCountryRepository = bucketCountryRepository
    }

    fun getCountryData(name: String) {
        mappRepository.getCountry(name,
            { result ->
                Log.d(TAG, "getCountry.success() called with: $result")
                // a livedata method to post a task to a main thread to set the given value (setvalue can be called from main thread)
                country.postValue(result)
            },
            {
                Log.d(TAG, "getCountry.error() called with: $it")
            },
            {
            }
        )
    }

    fun addCountryToBucketList(countryName: String) {
        mBucketCountryRepository.insertBucketCountry(countryName)
    }
}