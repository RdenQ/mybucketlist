package com.rdenq.bucketlist.data.repository

import android.util.Log
import com.rdenq.bucketlist.data.db.AppDatabase
import com.rdenq.bucketlist.data.model.BucketCountry
import com.rdenq.bucketlist.data.model.CountryAndBucketCountries
import com.rdenq.bucketlist.data.network.ApiDisposable
import com.rdenq.bucketlist.data.network.ApiError
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BucketCountryRepoImp(val database: AppDatabase) : BucketCountryRepository {

    private val TAG = BucketCountryRepoImp::class.java.simpleName

    override fun getBucketCountries(
        success: (MutableList<BucketCountry>) -> Unit,
        failure: (ApiError) -> Unit,
        terminate: () -> Unit
    ): Disposable =
        Observable
            .fromCallable { database.bucketCountryDao().selectAllBucketCountries() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnTerminate(terminate)
            .subscribeWith(
                ApiDisposable<MutableList<BucketCountry>>(
                    {
                        success(it)
                    },
                    failure
                )
            )

    override fun getBucketCountryForCountry(
        bcountryName: String,
        success: (BucketCountry) -> Unit,
        failure: (ApiError) -> Unit,
        terminate: () -> Unit
    ): Disposable =
        Observable
            .fromCallable { database.bucketCountryDao().getBucketCountryForCountry(bcountryName) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(
                ApiDisposable<BucketCountry>(
                    {
                        success(it)
                    },
                    failure
                )
            )

    override fun getBucketCountry(
        bucketCountryId: Long,
        success: (BucketCountry) -> Unit,
        failure: (ApiError) -> Unit,
        terminate: () -> Unit
    ): Disposable =
        Observable
            .fromCallable { database.bucketCountryDao().getBucketCountry(bucketCountryId) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(
                ApiDisposable<BucketCountry>(
                    {
                        success(it)
                    },
                    failure
                )
            )

     override fun getCountryAndBucketCountries(
        success: (MutableList<CountryAndBucketCountries>) -> Unit,
        failure: (ApiError) -> Unit,
        terminate: () -> Unit
    ): Disposable =
        Observable
            .fromCallable { database.bucketCountryDao().getCountryAndBucketCountries()}
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(
                ApiDisposable<MutableList<CountryAndBucketCountries>>(
                    {
                        success(it)
                    },
                    failure
                )
            )

    override fun insertBucketCountry(countryName: String): Disposable =
        Observable
            .fromCallable { database.bucketCountryDao().insertBucketCountry(BucketCountry(countryName)) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d(TAG, "bucketCountry added: subscribe: $it")
            }
}