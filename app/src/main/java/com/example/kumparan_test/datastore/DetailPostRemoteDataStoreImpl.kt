package com.example.kumparan_test.datastore
import com.example.kumparan_test.api.ApiInterface
import com.example.kumparan_test.exception.ApiResponseException
import com.example.kumparan_test.model.CommentResponse

import javax.inject.Inject


class DetailPostRemoteDataStoreImpl @Inject constructor(
        private val api: ApiInterface
) : DetailPostRemoteDataStore {

    override suspend fun getComment(postId: String): ArrayList<CommentResponse>? {
        try {
            val apiRes = api.getCommentList(postId).execute()
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