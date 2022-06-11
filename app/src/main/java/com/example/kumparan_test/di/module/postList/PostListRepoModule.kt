package com.example.kumparan_test.di.module.postList

import com.example.kumparan_test.repository.PostListRepository
import com.example.kumparan_test.repository.PostListRepositoryImpl

import dagger.Binds
import dagger.Module

@Module
interface PostListRepoModule {

    @Binds
    fun bindsDataListRepository(repo: PostListRepositoryImpl): PostListRepository
}