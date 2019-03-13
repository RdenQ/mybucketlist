package com.rdenq.bucketlist.di.builder

import com.rdenq.bucketlist.ui.BucketListFragment
import com.rdenq.bucketlist.ui.CountryDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.rdenq.bucketlist.ui.CountryListFragment

@Module
abstract class MainActivityProviders {
    @ContributesAndroidInjector
    abstract fun provideCountryListFragment(): CountryListFragment

    @ContributesAndroidInjector
    abstract fun provideBucketListFragment(): BucketListFragment

    @ContributesAndroidInjector
    abstract fun provideCountryDetailsFragment(): CountryDetailsFragment

}