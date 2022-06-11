package com.example.kumparan_test.di.component

import androidx.multidex.MultiDexApplication
import com.example.kumparan_test.di.module.NetworkModule
import com.example.kumparan_test.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, NetworkModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: BaseApp): AppComponent
    }
    fun postListTaskComponent(): PostListTaskComponent.Factory
    fun detailPostTaskComponent(): DetailPostTaskComponent.Factory
    fun userDetailTaskComponent(): UserDetailTaskComponent.Factory

}
class BaseApp: MultiDexApplication() {
    val appComponent = DaggerAppComponent.factory().create(this)
}