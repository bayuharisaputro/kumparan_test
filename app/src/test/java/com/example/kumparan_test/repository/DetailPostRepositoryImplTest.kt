package com.example.kumparan_test.repository

import com.example.kumparan_test.datastore.DetailPostRemoteDataStoreImpl
import com.example.kumparan_test.exception.NoInternetException
import com.example.kumparan_test.util.ConnectionUtil
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailPostRepositoryImplTest{
    @Mock
    var remoteDataStoreImpl : DetailPostRemoteDataStoreImpl? = null
    @Mock
    var netUtil : ConnectionUtil? = null

    @Before
    fun init(){
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getComment_normal() {
        runBlocking {
            Mockito.`when`(remoteDataStoreImpl?.getComment("1")).thenReturn(arrayListOf())
            Mockito.`when`(netUtil?.isInternetConnected()).thenReturn(true)
            val getDataRepository : DetailPostRepositoryImpl = DetailPostRepositoryImpl(remoteDataStoreImpl!!, netUtil!!)

            val res = getDataRepository?.getCommentList("1")
            Truth.assertThat(res).isNotNull()
        }
    }

    @Test
    fun getComment_Error() {
        runBlocking {
            Mockito.`when`(remoteDataStoreImpl?.getComment("1")).thenReturn(arrayListOf())
            Mockito.`when`(netUtil?.isInternetConnected()).thenReturn(false)
            val getDataRepository : DetailPostRepositoryImpl = DetailPostRepositoryImpl(remoteDataStoreImpl!!, netUtil!!)

            try {
                getDataRepository.getCommentList("1")
                throw RuntimeException("Is must not be here")
            }
            catch (e : NoInternetException) {
                //expected to throw No Internet Exception
            }
        }
    }

}