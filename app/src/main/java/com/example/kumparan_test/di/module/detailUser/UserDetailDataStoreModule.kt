package com.example.kumparan_test.di.module.detailUser

import com.example.kumparan_test.datastore.UserDetailRemoteDataStore
import com.example.kumparan_test.datastore.UserDetailRemoteDataStoreImpl
import dagger.Binds
import dagger.Module

@Module
interface UserDetailDataStoreModule {

    @Binds
    fun bindsUserDetailRemoteDataStore(remoteDataStore: UserDetailRemoteDataStoreImpl): UserDetailRemoteDataStore
}