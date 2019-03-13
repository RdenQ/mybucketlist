package com.rdenq.bucketlist.di.module

import android.app.Application
import android.content.Context
import com.rdenq.bucketlist.di.builder.ViewModelBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelBuilder::class])
class ContextModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

//    @Provides
//    @Singleton
//    fun provideSvgLoader(activity: Activity): SvgLoader {
//        return SvgLoader.pluck().with(activity)
//    }
}