package com.rdenq.bucketlist.di.builder

import androidx.lifecycle.ViewModel
import com.rdenq.bucketlist.di.qualifier.ViewModelKey
import com.rdenq.bucketlist.ui.viewmodels.BucketListViewModel
import com.rdenq.bucketlist.ui.viewmodels.CountryDetailsViewModel
import com.rdenq.bucketlist.ui.viewmodels.CountryListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(CountryListViewModel::class)
    abstract fun bindCountryListViewModel(countryListViewModel: CountryListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CountryDetailsViewModel::class)
    abstract fun bindCountryDetailsViewModel(countryDetailsViewModel: CountryDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BucketListViewModel::class)
    abstract fun bindBucketListViewModel(bucketListViewModel: BucketListViewModel): ViewModel
}