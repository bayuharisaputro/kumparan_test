package com.example.kumparan_test.datastore

import com.example.kumparan_test.api.ApiInterface
import com.example.kumparan_test.model.CommentResponse
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class DetailPostRemoteDataStoreImplTest {
    @Mock
    val apiInterface : ApiInterface? = null

    @Mock
    val response : Response<ArrayList<CommentResponse>>? = null

    @Mock
    val callRetrofit : Call<ArrayList<CommentResponse>>? = null

    @Before
    fun init(){
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(apiInterface?.getCommentList("1")).thenReturn(callRetrofit)
    }

    @Test
    fun getComment_normal() {
        runBlocking {
            Mockito.`when`(callRetrofit?.execute()).thenReturn(response)
            Mockito.`when`(response?.body()).thenReturn(arrayListOf(CommentResponse(), CommentResponse(), CommentResponse()))
            Mockito.`when`(response?.isSuccessful).thenReturn(true)
            val remoteGetData = DetailPostRemoteDataStoreImpl(apiInterface!!)
            val res = remoteGetData.getComment("1")
            Truth.assertThat(res).isNotNull()
            Truth.assertThat(res).hasSize(3)
        }
    }

    @Test
    fun getComment_error() {
        runBlocking {
            Mockito.`when`(callRetrofit?.execute()).thenThrow(IOException("Error"))
            val remoteGetData = DetailPostRemoteDataStoreImpl(apiInterface!!)
            try{
                remoteGetData.getComment("1")
                throw RuntimeException("Is must not be here")
            }
            catch (e : Exception){
                Truth.assertThat(e.message).isEqualTo("Error")
            }
        }
    }
}