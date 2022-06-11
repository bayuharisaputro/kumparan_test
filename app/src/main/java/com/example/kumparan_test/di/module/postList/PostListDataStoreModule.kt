package com.example.kumparan_test.di.module.postList

import com.example.kumparan_test.datastore.PostListRemoteDataStore
import com.example.kumparan_test.datastore.PostListRemoteDataStoreImpl
import dagger.Binds
import dagger.Module

@Module
interface PostListDataStoreModule {

    @Binds
    fun bindsPostListRemoteDataStore(remoteDataStore: PostListRemoteDataStoreImpl): PostListRemoteDataStore
}