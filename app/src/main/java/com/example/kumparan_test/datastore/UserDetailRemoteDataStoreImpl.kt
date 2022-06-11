package com.example.kumparan_test.datastore
import com.example.kumparan_test.api.ApiInterface
import com.example.kumparan_test.exception.ApiResponseException
import com.example.kumparan_test.model.AlbumResponse
import com.example.kumparan_test.model.PhotoResponse

import javax.inject.Inject
import kotlin.jvm.Throws


class UserDetailRemoteDataStoreImpl @Inject constructor(
        private val api: ApiInterface
) : UserDetailRemoteDataStore {

    @Throws(Exception::class)
    override suspend fun getAlbumList(userId: String): ArrayList<AlbumResponse>? {
        try {
            val apiRes = api.getUserAlbums(userId).execute()
            try {
                if (apiRes.isSuccessful) {
                    return apiRes.body()
                } else {
                    throw ApiResponseException(apiRes.code().toString(), apiRes.message())
                }
            } catch (e: Exception) {
                throw e
            }
        } catch (e: java.lang.Exception) {
            throw e
        }
    }

    @Throws(Exception::class)
    override suspend fun getPhotoList(): ArrayList<PhotoResponse>? {
        try {
            val apiRes = api.getPhotoList().execute()
            try {
                if (apiRes.isSuccessful) {
                    return apiRes.body()
                } else {
                    throw ApiResponseException(apiRes.code().toString(), apiRes.message())
                }
            } catch (e: Exception) {
                throw e
            }
        } catch (e: java.lang.Exception) {
            throw e
        }
    }
}