package com.rdenq.bucketlist.core

import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import com.rdenq.bucketlist.di.component.DaggerCoreComponent

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        // DaggerCoreComponent is the implementation of CoreComp created at compile time by dagger lib. not to be edited.
        // Contains all the code to create the objects injected with dagger: ViewModelFactory and CountryListViewModel
        return DaggerCoreComponent
            .builder()
            .application(this)
            .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}