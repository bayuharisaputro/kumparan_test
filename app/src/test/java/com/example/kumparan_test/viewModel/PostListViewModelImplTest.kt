package com.example.kumparan_test.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kumparan_test.repository.PostListRepositoryImpl
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PostListViewModelImplTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    var repo: PostListRepositoryImpl? = null

    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun requestGetPostList_normal() {
        runBlocking {
            val vm = PostListViewModelImpl(repo!!)
            Mockito.`when`(repo?.getPostList()).thenReturn(arrayListOf())
            Mockito.`when`(repo?.getUserList()).thenReturn(arrayListOf())

            vm.requestGetPostList()

            val dataList = vm.ldPostList.value
            val error = vm.ldErrorHandler.value
            val isLoading = vm.ldIsLoading.value
            Truth.assertThat(dataList).isNotNull()
            Truth.assertThat(error).isNull()
            Truth.assertThat(isLoading).isFalse()
        }
    }

    @Test
    fun requestGetPostList_error() {
        runBlocking {
            val vm = PostListViewModelImpl(repo!!)
            Mockito.`when`(repo?.getPostList()).thenThrow(IOException("error"))
            Mockito.`when`(repo?.getUserList()).thenThrow(IOException("error"))

            vm.requestGetPostList()

            val dataList = vm.ldPostList.value
            val error = vm.ldErrorHandler.value
            val isLoading = vm.ldIsLoading.value
            Truth.assertThat(dataList).isNull()
            Truth.assertThat(error).isNotNull()
            Truth.assertThat(isLoading).isFalse()
        }
    }
}