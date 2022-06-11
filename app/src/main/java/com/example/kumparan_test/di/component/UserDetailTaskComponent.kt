package com.example.kumparan_test.di.component

import com.example.kumparan_test.UserActivity
import com.example.kumparan_test.di.module.detailUser.UserDetailDataStoreModule
import com.example.kumparan_test.di.module.detailUser.UserDetailRepoModule
import com.example.kumparan_test.di.module.detailUser.UserDetailTaskModule
import dagger.Subcomponent

@Subcomponent(
        modules = [UserDetailDataStoreModule::class,
            UserDetailRepoModule::class,
            UserDetailTaskModule::class
        ]
)
interface UserDetailTaskComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): UserDetailTaskComponent
    }

    fun inject(activity: UserActivity)
}