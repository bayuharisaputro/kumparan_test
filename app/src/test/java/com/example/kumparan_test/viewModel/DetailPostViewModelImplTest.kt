package com.example.kumparan_test.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kumparan_test.repository.DetailPostRepositoryImpl
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
class DetailPostViewModelImplTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    var repo: DetailPostRepositoryImpl? = null

    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun requestGetCommentList() {
        runBlocking {
            val vm = DetailPostViewModelImpl(repo!!)
            Mockito.`when`(repo?.getCommentList("1")).thenReturn(arrayListOf())

            vm.requestGetCommentList("1")

            val dataList = vm.ldCommentList.value
            val error = vm.ldErrorHandler.value
            val isLoading = vm.ldIsLoading.value
            Truth.assertThat(dataList).isNotNull()
            Truth.assertThat(error).isNull()
            Truth.assertThat(isLoading).isFalse()
        }
    }

    @Test
    fun requestGetCommentList_error() {
        runBlocking {
            val vm = DetailPostViewModelImpl(repo!!)
            Mockito.`when`(repo?.getCommentList("1")).thenThrow(IOException("error"))

            vm.requestGetCommentList("1")

            val dataList = vm.ldCommentList.value
            val error = vm.ldErrorHandler.value
            val isLoading = vm.ldIsLoading.value
            Truth.assertThat(dataList).isNull()
            Truth.assertThat(error).isNotNull()
            Truth.assertThat(isLoading).isFalse()
        }
    }
}