package com.example.kumparan_test.datastore

import com.example.kumparan_test.api.ApiInterface
import com.example.kumparan_test.model.PostResponse
import com.example.kumparan_test.model.UserResponse
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

class PostListRemoteDataStoreImplTest {
    @Mock
    val apiInterface : ApiInterface? = null

    @Mock
    val responsePost : Response<ArrayList<PostResponse>>? = null

    @Mock
    val responseUser : Response<ArrayList<UserResponse>>? = null

    @Mock
    val callRetrofitPost : Call<ArrayList<PostResponse>>? = null

    @Mock
    val callRetrofitUser : Call<ArrayList<UserResponse>>? = null

    @Before
    fun init(){
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(apiInterface?.getUserList()).thenReturn(callRetrofitUser)
        Mockito.`when`(apiInterface?.getPostList()).thenReturn(callRetrofitPost)
    }

    @Test
    fun getPostList_normal() {
        runBlocking {
            Mockito.`when`(callRetrofitPost?.execute()).thenReturn(responsePost)
            Mockito.`when`(responsePost?.body()).thenReturn(arrayListOf(PostResponse(), PostResponse(), PostResponse()))
            Mockito.`when`(responsePost?.isSuccessful).thenReturn(true)
            val remoteGetData = PostListRemoteDataStoreImpl(apiInterface!!)
            val res = remoteGetData.getPostList()
            Truth.assertThat(res).isNotNull()
            Truth.assertThat(res).hasSize(3)
        }
    }

    @Test
    fun getPostList_error() {
        runBlocking {
            Mockito.`when`(callRetrofitPost?.execute()).thenThrow(IOException("Error"))
            val remoteGetData = PostListRemoteDataStoreImpl(apiInterface!!)
            try{
                remoteGetData.getPostList()
                throw RuntimeException("Is must not be here")
            }
            catch (e : Exception){
                Truth.assertThat(e.message).isEqualTo("Error")
            }
        }
    }

    @Test
    fun getUserList_normal() {
        runBlocking {
            Mockito.`when`(callRetrofitUser?.execute()).thenReturn(responseUser)
            Mockito.`when`(responseUser?.body()).thenReturn(arrayListOf(UserResponse(), UserResponse(), UserResponse()))
            Mockito.`when`(responseUser?.isSuccessful).thenReturn(true)
            val remoteGetData = PostListRemoteDataStoreImpl(apiInterface!!)
            val res = remoteGetData.getUserList()
            Truth.assertThat(res).isNotNull()
            Truth.assertThat(res).hasSize(3)
        }
    }

    @Test
    fun getUserList_error() {
        runBlocking {
            Mockito.`when`(callRetrofitUser?.execute()).thenThrow(IOException("Error"))
            val remoteGetData = PostListRemoteDataStoreImpl(apiInterface!!)
            try{
                remoteGetData.getUserList()
                throw RuntimeException("Is must not be here")
            }
            catch (e : Exception){
                Truth.assertThat(e.message).isEqualTo("Error")
            }
        }
    }
}