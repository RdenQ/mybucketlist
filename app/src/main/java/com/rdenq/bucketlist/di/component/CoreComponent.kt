package com.rdenq.bucketlist.di.component

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.rdenq.bucketlist.core.App
import com.rdenq.bucketlist.di.builder.ActivityBuilder
import com.rdenq.bucketlist.di.module.ContextModule
import com.rdenq.bucketlist.di.module.DataBaseModule
import com.rdenq.bucketlist.di.module.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, NetworkModule::class,  ActivityBuilder::class,
     DataBaseModule::class, ContextModule::class])
interface CoreComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CoreComponent
    }


}