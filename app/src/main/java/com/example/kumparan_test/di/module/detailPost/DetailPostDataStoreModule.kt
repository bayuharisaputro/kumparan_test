package com.example.kumparan_test.di.module.detailPost

import com.example.kumparan_test.datastore.DetailPostRemoteDataStore
import com.example.kumparan_test.datastore.DetailPostRemoteDataStoreImpl
import dagger.Binds
import dagger.Module

@Module
interface DetailPostDataStoreModule {

    @Binds
    fun bindsDetailPostRemoteDataStore(remoteDataStore: DetailPostRemoteDataStoreImpl): DetailPostRemoteDataStore
}