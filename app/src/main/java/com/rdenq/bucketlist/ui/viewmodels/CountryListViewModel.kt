package com.rdenq.bucketlist.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rdenq.bucketlist.data.model.Country
import com.rdenq.bucketlist.data.network.ApiError
import com.rdenq.bucketlist.data.repository.AppRepository
import javax.inject.Inject

class CountryListViewModel @Inject constructor(appRepository: AppRepository) : BaseViewModel() {

    private val TAG = CountryListViewModel::class.java.simpleName
    val countryList: MutableLiveData<MutableList<Country>> by lazy { MutableLiveData<MutableList<Country>>() }
    val error: MutableLiveData<ApiError> by lazy { MutableLiveData<ApiError>() }

    init {
        appRepository.getCountries(
            { listOfCountries ->
                Log.d(TAG, "getCountryList.success() called with: $listOfCountries")
                // a livedata method to post a task to a main thread to set the given value (setvalue can be called from main thread)
                countryList.postValue(listOfCountries)
                if (listOfCountries.size > 0) {
                    for (country in listOfCountries) {
                        appRepository.insertCountry(country).also { compositeDisposable.add(it) }
                    }
                }
            },
            {
                Log.d(TAG, "getCountryList.error() called with: $it")
                error.value = it
            },
            {
            }
        ).also { compositeDisposable.add(it) }
    }
}