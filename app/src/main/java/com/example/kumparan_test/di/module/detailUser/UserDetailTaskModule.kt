package com.example.kumparan_test.di.module.detailUser

import androidx.lifecycle.ViewModel
import com.example.kumparan_test.di.module.ViewModelKey
import com.example.kumparan_test.viewModel.UserDetailViewModel
import com.example.kumparan_test.viewModel.UserDetailViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface UserDetailTaskModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModelImpl::class)
    fun bindViewModelImpl(viewModel: UserDetailViewModelImpl): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    fun bindViewModel(viewModel: UserDetailViewModelImpl): ViewModel
}