package com.example.kumparan_test.repository

import com.example.kumparan_test.datastore.PostListRemoteDataStoreImpl
import com.example.kumparan_test.exception.NoInternetException
import com.example.kumparan_test.util.ConnectionUtil
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PostListRepositoryImplTest {
    @Mock
    var remoteDataStoreImpl : PostListRemoteDataStoreImpl? = null
    @Mock
    var netUtil : ConnectionUtil? = null

    @Before
    fun init(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getPostList_normal() {
        runBlocking {
            Mockito.`when`(remoteDataStoreImpl?.getPostList()).thenReturn(arrayListOf())
            Mockito.`when`(netUtil?.isInternetConnected()).thenReturn(true)
            val getDataRepository : PostListRepositoryImpl = PostListRepositoryImpl(remoteDataStoreImpl!!, netUtil!!)

            val res = getDataRepository.getPostList()
            Truth.assertThat(res).isNotNull()
        }
    }

    @Test
    fun getPostList_Error() {
        runBlocking {
            Mockito.`when`(remoteDataStoreImpl?.getPostList()).thenReturn(arrayListOf())
            Mockito.`when`(netUtil?.isInternetConnected()).thenReturn(false)
            val getDataRepository : PostListRepositoryImpl = PostListRepositoryImpl(remoteDataStoreImpl!!, netUtil!!)

            try {
                getDataRepository.getPostList()
                throw RuntimeException("Is must not be here")
            }
            catch (e : NoInternetException) {
                //expected to throw No Internet Exception
            }
        }
    }

    @Test
    fun getUserList_normal() {
        runBlocking {
            Mockito.`when`(remoteDataStoreImpl?.getUserList()).thenReturn(arrayListOf())
            Mockito.`when`(netUtil?.isInternetConnected()).thenReturn(true)
            val getDataRepository : PostListRepositoryImpl = PostListRepositoryImpl(remoteDataStoreImpl!!, netUtil!!)

            val res = getDataRepository.getUserList()
            Truth.assertThat(res).isNotNull()
        }
    }

    @Test
    fun getUserList_Error() {
        runBlocking {
            Mockito.`when`(remoteDataStoreImpl?.getUserList()).thenReturn(arrayListOf())
            Mockito.`when`(netUtil?.isInternetConnected()).thenReturn(false)
            val getDataRepository : PostListRepositoryImpl = PostListRepositoryImpl(remoteDataStoreImpl!!, netUtil!!)

            try {
                getDataRepository.getUserList()
                throw RuntimeException("Is must not be here")
            }
            catch (e : NoInternetException) {
                //expected to throw No Internet Exception
            }
        }
    }
}