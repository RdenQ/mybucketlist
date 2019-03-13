package com.rdenq.bucketlist.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.rdenq.bucketlist.ui.MainActivity

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityProviders::class])
    abstract fun bindMainActivity(): MainActivity
}