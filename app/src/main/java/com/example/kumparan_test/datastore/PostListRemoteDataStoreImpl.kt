package com.example.kumparan_test.datastore
import com.example.kumparan_test.api.ApiInterface
import com.example.kumparan_test.model.PostResponse
import com.example.kumparan_test.exception.ApiResponseException
import com.example.kumparan_test.model.UserResponse

import javax.inject.Inject
import kotlin.jvm.Throws


class PostListRemoteDataStoreImpl @Inject constructor(
        private val api: ApiInterface
) : PostListRemoteDataStore {

    @Throws(Exception::class)
    override suspend fun getPostList(): ArrayList<PostResponse>? {
        try {
            val apiRes = api.getPostList().execute()
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
    override suspend fun getUserList(): ArrayList<UserResponse>? {
        try {
            val apiRes = api.getUserList().execute()
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