package com.example.kumparan_test.repository

import com.example.kumparan_test.datastore.PostListRemoteDataStore
import com.example.kumparan_test.exception.NoInternetException
import com.example.kumparan_test.model.PostResponse
import com.example.kumparan_test.model.UserResponse
import com.example.kumparan_test.util.ConnectionUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.jvm.Throws

class PostListRepositoryImpl @Inject constructor(
    private val remoteDataStore: PostListRemoteDataStore,
    private val networkUtil: ConnectionUtil
) : PostListRepository {
    @Throws(Exception::class)
    override suspend fun getPostList(): ArrayList<PostResponse>? = withContext(Dispatchers.IO){
        if (networkUtil.isInternetConnected()) {
            return@withContext remoteDataStore.getPostList()
        }
        else {
            throw NoInternetException()
        }
    }

    @Throws(Exception::class)
    override suspend fun getUserList(): ArrayList<UserResponse>? = withContext(Dispatchers.IO) {
        if (networkUtil.isInternetConnected()) {
            return@withContext remoteDataStore.getUserList()
        }
        else {
            throw NoInternetException()
        }
    }


}