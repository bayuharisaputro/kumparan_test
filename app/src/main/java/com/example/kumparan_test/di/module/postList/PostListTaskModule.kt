package com.example.kumparan_test.di.module.postList

import androidx.lifecycle.ViewModel
import com.example.kumparan_test.di.module.ViewModelKey
import com.example.kumparan_test.viewModel.PostListViewModel
import com.example.kumparan_test.viewModel.PostListViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PostListTaskModule {
    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModelImpl::class)
    fun bindViewModelImpl(viewModel: PostListViewModelImpl): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostListViewModel::class)
    fun bindViewModel(viewModel: PostListViewModelImpl): ViewModel
}