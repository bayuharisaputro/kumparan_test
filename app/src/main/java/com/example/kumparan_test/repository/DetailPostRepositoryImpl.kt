package com.example.kumparan_test.repository

import com.example.kumparan_test.datastore.DetailPostRemoteDataStore
import com.example.kumparan_test.exception.NoInternetException
import com.example.kumparan_test.model.CommentResponse
import com.example.kumparan_test.util.ConnectionUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.jvm.Throws

class DetailPostRepositoryImpl @Inject constructor(
    private val remoteDataStore: DetailPostRemoteDataStore,
    private val networkUtil: ConnectionUtil
) : DetailPostRepository {

    @Throws(Exception::class)
    override suspend fun getCommentList(postId: String): ArrayList<CommentResponse>? = withContext(Dispatchers.IO) {
        if (networkUtil.isInternetConnected()) {
            return@withContext remoteDataStore.getComment(postId)
        }
        else {
            throw NoInternetException()
        }
    }


}