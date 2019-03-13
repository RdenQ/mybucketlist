package com.rdenq.bucketlist.data.network

import io.reactivex.Observable
import com.rdenq.bucketlist.data.model.Country
import retrofit2.http.GET

interface ApiService {

    @GET("rest/v2/all")
    fun getAllCountries(): Observable<MutableList<Country>>


}