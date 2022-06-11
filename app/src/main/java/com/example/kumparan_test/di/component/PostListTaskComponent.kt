package com.example.kumparan_test.di.component

import com.example.kumparan_test.MainActivity
import com.example.kumparan_test.di.module.postList.PostListDataStoreModule
import com.example.kumparan_test.di.module.postList.PostListRepoModule
import com.example.kumparan_test.di.module.postList.PostListTaskModule
import dagger.Subcomponent

@Subcomponent(
        modules = [PostListDataStoreModule::class,
            PostListRepoModule::class,
            PostListTaskModule::class
        ]
)
interface PostListTaskComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): PostListTaskComponent
    }

    fun inject(activity: MainActivity)
}