package com.example.kumparan_test.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kumparan_test.repository.PostListRepositoryImpl
import com.example.kumparan_test.repository.UserDetailRepositoryImpl
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class UserDetailViewModelImplTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    var repo: UserDetailRepositoryImpl? = null

    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun requestGetAlbumList_normal() {
        runBlocking {
            val vm = UserDetailViewModelImpl(repo!!)
            Mockito.`when`(repo?.getAlbumList("1")).thenReturn(arrayListOf())
            Mockito.`when`(repo?.getPhotoList()).thenReturn(arrayListOf())

            vm.requestGetAlbumList("1")

            val dataList = vm.ldAlbumList.value
            val error = vm.ldErrorHandler.value
            val isLoading = vm.ldIsLoading.value
            Truth.assertThat(dataList).isNotNull()
            Truth.assertThat(error).isNull()
            Truth.assertThat(isLoading).isFalse()
        }
    }

    @Test
    fun requestGetAlbumList_error() {
        runBlocking {
            val vm = UserDetailViewModelImpl(repo!!)
            Mockito.`when`(repo?.getAlbumList("1")).thenThrow(IOException("error"))
            Mockito.`when`(repo?.getPhotoList()).thenThrow(IOException("error"))

            vm.requestGetAlbumList("1")

            val dataList = vm.ldAlbumList.value
            val error = vm.ldErrorHandler.value
            val isLoading = vm.ldIsLoading.value
            Truth.assertThat(dataList).isNull()
            Truth.assertThat(error).isNotNull()
            Truth.assertThat(isLoading).isFalse()
        }
    }
}