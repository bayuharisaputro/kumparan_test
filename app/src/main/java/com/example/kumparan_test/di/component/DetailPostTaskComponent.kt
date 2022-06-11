package com.example.kumparan_test.di.component

import com.example.kumparan_test.DetailActivity
import com.example.kumparan_test.di.module.detailPost.DetailPostDataStoreModule
import com.example.kumparan_test.di.module.detailPost.DetailPostRepoModule
import com.example.kumparan_test.di.module.detailPost.DetailPostTaskModule
import dagger.Subcomponent

@Subcomponent(
        modules = [DetailPostDataStoreModule::class,
            DetailPostRepoModule::class,
            DetailPostTaskModule::class
        ]
)
interface DetailPostTaskComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): DetailPostTaskComponent
    }

    fun inject(activity: DetailActivity)
}