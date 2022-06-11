package com.example.kumparan_test.di.module.detailPost

import com.example.kumparan_test.repository.DetailPostRepository
import com.example.kumparan_test.repository.DetailPostRepositoryImpl

import dagger.Binds
import dagger.Module

@Module
interface DetailPostRepoModule {

    @Binds
    fun bindsDetailPostRepository(repo: DetailPostRepositoryImpl): DetailPostRepository
}