package com.example.kumparan_test.repository

import com.example.kumparan_test.datastore.UserDetailRemoteDataStoreImpl
import com.example.kumparan_test.exception.NoInternetException
import com.example.kumparan_test.util.ConnectionUtil
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class UserDetailRepositoryImplTest {
    @Mock
    var remoteDataStoreImpl : UserDetailRemoteDataStoreImpl? = null
    @Mock
    var netUtil : ConnectionUtil? = null

    @Before
    fun init(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getAlbumList_normal() {
        runBlocking {
            Mockito.`when`(remoteDataStoreImpl?.getAlbumList("1")).thenReturn(arrayListOf())
            Mockito.`when`(netUtil?.isInternetConnected()).thenReturn(true)
            val getDataRepository = UserDetailRepositoryImpl(remoteDataStoreImpl!!, netUtil!!)

            val res = getDataRepository.getAlbumList("1")
            Truth.assertThat(res).isNotNull()
        }
    }

    @Test
    fun getAlbumList_Error() {
        runBlocking {
            Mockito.`when`(remoteDataStoreImpl?.getAlbumList("1")).thenReturn(arrayListOf())
            Mockito.`when`(netUtil?.isInternetConnected()).thenReturn(false)
            val getDataRepository = UserDetailRepositoryImpl(remoteDataStoreImpl!!, netUtil!!)

            try {
                getDataRepository.getAlbumList("1")
                throw RuntimeException("Is must not be here")
            }
            catch (e : NoInternetException) {
                //expected to throw No Internet Exception
            }
        }
    }

    @Test
    fun getPhotoList_normal() {
        runBlocking {
            Mockito.`when`(remoteDataStoreImpl?.getPhotoList()).thenReturn(arrayListOf())
            Mockito.`when`(netUtil?.isInternetConnected()).thenReturn(true)
            val getDataRepository = UserDetailRepositoryImpl(remoteDataStoreImpl!!, netUtil!!)

            val res = getDataRepository.getPhotoList()
            Truth.assertThat(res).isNotNull()
        }
    }

    @Test
    fun getPhotoList_Error() {
        runBlocking {
            Mockito.`when`(remoteDataStoreImpl?.getPhotoList()).thenReturn(arrayListOf())
            Mockito.`when`(netUtil?.isInternetConnected()).thenReturn(false)
            val getDataRepository = UserDetailRepositoryImpl(remoteDataStoreImpl!!, netUtil!!)

            try {
                getDataRepository.getPhotoList()
                throw RuntimeException("Is must not be here")
            }
            catch (e : NoInternetException) {
                //expected to throw No Internet Exception
            }
        }
    }
}