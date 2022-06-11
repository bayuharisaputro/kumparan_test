package com.example.kumparan_test.di.module.detailPost

import androidx.lifecycle.ViewModel
import com.example.kumparan_test.di.module.ViewModelKey
import com.example.kumparan_test.viewModel.DetailPostViewModel
import com.example.kumparan_test.viewModel.DetailPostViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DetailPostTaskModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailPostViewModelImpl::class)
    fun bindViewModelImpl(viewModel: DetailPostViewModelImpl): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailPostViewModel::class)
    fun bindViewModel(viewModel: DetailPostViewModelImpl): ViewModel
}