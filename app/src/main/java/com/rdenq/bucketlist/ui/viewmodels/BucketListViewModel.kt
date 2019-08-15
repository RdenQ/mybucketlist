package com.rdenq.bucketlist.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rdenq.bucketlist.data.model.BucketCountry
import com.rdenq.bucketlist.data.model.CountryAndBucketCountries
import com.rdenq.bucketlist.data.network.ApiError
import com.rdenq.bucketlist.data.repository.BucketCountryRepository
import javax.inject.Inject

class BucketListViewModel @Inject constructor(bucketCountryRepository: BucketCountryRepository) : BaseViewModel() {

    private val TAG = BucketListViewModel::class.java.simpleName

    val bucketCountryList: MutableLiveData<MutableList<BucketCountry>> by lazy { MutableLiveData<MutableList<BucketCountry>>() }
    val error2 : MutableLiveData<ApiError> by lazy { MutableLiveData<ApiError>() }
    val countryAndBucketCountryList: MutableLiveData<MutableList<CountryAndBucketCountries>> by lazy { MutableLiveData<MutableList<CountryAndBucketCountries>>() }
    val error3 : MutableLiveData<ApiError> by lazy { MutableLiveData<ApiError>() }

    init {
        bucketCountryRepository.getBucketCountries(
            { listOfBucketCountries ->
                Log.d(TAG, "bucketCountryList.success() called with: $listOfBucketCountries")
                // a livedata method to post a task to a main thread to set the given value (setvalue can be called from main thread)
                bucketCountryList.postValue(listOfBucketCountries)
            },
            {
                Log.d(TAG, "bucketCountryList.error() called with: $it")
                error2.value = it
            },
            {
            }
        ).also { compositeDisposable.add(it) }

        bucketCountryRepository.getCountryAndBucketCountries(
            { result ->
                Log.d(TAG, "countryAndBucketCountryList.success() called with: $result")
                val addedToBucketList = result.filter {it.bucketListCountries.isNotEmpty() }.toMutableList()
                // a livedata method to post a task to a main thread to set the given value (setvalue can be called from main thread)
                countryAndBucketCountryList.postValue(addedToBucketList)
            },
            {
                Log.d(TAG, "countryAndBucketCountryList.error() called with: $it")
                error3.value = it
            },
            {
            }
        ).also { compositeDisposable.add(it) }
    }
}