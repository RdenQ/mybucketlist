package com.rdenq.bucketlist.data.repository

import android.util.Log
import com.rdenq.bucketlist.data.db.AppDatabase
import com.rdenq.bucketlist.data.model.Country
import com.rdenq.bucketlist.data.network.ApiDisposable
import com.rdenq.bucketlist.data.network.ApiError
import com.rdenq.bucketlist.data.network.ApiService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class AppRepoImp(val apiService: ApiService, val database: AppDatabase) : AppRepository {

    private val TAG = AppRepoImp::class.java.simpleName

    override fun getCountries(
        success: (MutableList<Country>) -> Unit,
        failure: (ApiError) -> Unit,
        terminate: () -> Unit
    ): Disposable {
        return apiService
            .getAllCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate(terminate)
            .subscribeWith(
                ApiDisposable<MutableList<Country>>(
                    {
                        success(it)
                    },
                    failure
                )
            )
    }

    override fun getCountry(
        countryName: String, success: (Country) -> Unit,
        failure: (ApiError) -> Unit, terminate: () -> Unit
    ): Disposable =
        Observable
            .fromCallable { database.countryDao().getCountry(countryName) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(
                ApiDisposable<Country>(
                    {
                        success(it)
                    },
                    failure
                )
            )

    override fun insertCountry(country: Country): Disposable =
        Observable
            .fromCallable { database.countryDao().insertCountry(country) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "country added to DB: subscribe: $it")
            }
}