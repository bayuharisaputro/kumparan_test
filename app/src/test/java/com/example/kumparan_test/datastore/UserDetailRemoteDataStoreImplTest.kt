package com.example.kumparan_test.datastore

import com.example.kumparan_test.api.ApiInterface
import com.example.kumparan_test.model.*
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

class UserDetailRemoteDataStoreImplTest {

    @Mock
    val apiInterface : ApiInterface? = null

    @Mock
    val responseAlbum : Response<ArrayList<AlbumResponse>>? = null

    @Mock
    val responsePhoto : Response<ArrayList<PhotoResponse>>? = null

    @Mock
    val callRetrofitAlbum : Call<ArrayList<AlbumResponse>>? = null

    @Mock
    val callRetrofitPhoto : Call<ArrayList<PhotoResponse>>? = null

    @Before
    fun init(){
        MockitoAnnotations.openMocks(this)
        Mockito.`when`(apiInterface?.getUserAlbums("1")).thenReturn(callRetrofitAlbum)
        Mockito.`when`(apiInterface?.getPhotoList()).thenReturn(callRetrofitPhoto)
    }

    @Test
    fun getAlbumList_normal() {
        runBlocking {
            Mockito.`when`(callRetrofitAlbum?.execute()).thenReturn(responseAlbum)
            Mockito.`when`(responseAlbum?.body()).thenReturn(arrayListOf(AlbumResponse(), AlbumResponse(), AlbumResponse()))
            Mockito.`when`(responseAlbum?.isSuccessful).thenReturn(true)
            val remoteGetData = UserDetailRemoteDataStoreImpl(apiInterface!!)
            val res = remoteGetData.getAlbumList("1")
            Truth.assertThat(res).isNotNull()
            Truth.assertThat(res).hasSize(3)
        }
    }

    @Test
    fun getAlbumList_error() {
        runBlocking {
            Mockito.`when`(callRetrofitAlbum?.execute()).thenThrow(IOException("Error"))
            val remoteGetData = UserDetailRemoteDataStoreImpl(apiInterface!!)
            try{
                remoteGetData.getAlbumList("1")
                throw RuntimeException("Is must not be here")
            }
            catch (e : Exception){
                Truth.assertThat(e.message).isEqualTo("Error")
            }
        }
    }

    @Test
    fun getPhotoList() {
        runBlocking {
            Mockito.`when`(callRetrofitPhoto?.execute()).thenReturn(responsePhoto)
            Mockito.`when`(responsePhoto?.body()).thenReturn(arrayListOf(PhotoResponse(), PhotoResponse(), PhotoResponse()))
            Mockito.`when`(responsePhoto?.isSuccessful).thenReturn(true)
            val remoteGetData = UserDetailRemoteDataStoreImpl(apiInterface!!)
            val res = remoteGetData.getPhotoList()
            Truth.assertThat(res).isNotNull()
            Truth.assertThat(res).hasSize(3)
        }
    }

    @Test
    fun getPhotoList_error() {
        runBlocking {
            Mockito.`when`(callRetrofitPhoto?.execute()).thenThrow(IOException("Error"))
            val remoteGetData = UserDetailRemoteDataStoreImpl(apiInterface!!)
            try{
                remoteGetData.getPhotoList()
                throw RuntimeException("Is must not be here")
            }
            catch (e : Exception){
                Truth.assertThat(e.message).isEqualTo("Error")
            }
        }
    }
}