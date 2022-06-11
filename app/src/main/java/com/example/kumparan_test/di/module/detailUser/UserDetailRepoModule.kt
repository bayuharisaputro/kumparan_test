package com.example.kumparan_test.di.module.detailUser

import com.example.kumparan_test.repository.UserDetailRepository
import com.example.kumparan_test.repository.UserDetailRepositoryImpl

import dagger.Binds
import dagger.Module

@Module
interface UserDetailRepoModule {

    @Binds
    fun bindsUserDetailRepository(repo: UserDetailRepositoryImpl): UserDetailRepository
}