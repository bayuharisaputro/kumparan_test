package com.example.kumparan_test.repository

import com.example.kumparan_test.datastore.UserDetailRemoteDataStore
import com.example.kumparan_test.exception.NoInternetException
import com.example.kumparan_test.model.AlbumResponse
import com.example.kumparan_test.model.PhotoResponse
import com.example.kumparan_test.util.ConnectionUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.jvm.Throws

class UserDetailRepositoryImpl @Inject constructor(
    private val remoteDataStore: UserDetailRemoteDataStore,
    private val networkUtil: ConnectionUtil
) : UserDetailRepository {

    @Throws(Exception::class)
    override suspend fun getAlbumList(userId: String): ArrayList<AlbumResponse>? = withContext(Dispatchers.IO)  {
        if (networkUtil.isInternetConnected()) {
            return@withContext remoteDataStore.getAlbumList(userId)
        }
        else {
            throw NoInternetException()
        }
    }

    @Throws(Exception::class)
    override suspend fun getPhotoList(): ArrayList<PhotoResponse>? = withContext(Dispatchers.IO) {
        if (networkUtil.isInternetConnected()) {
            return@withContext remoteDataStore.getPhotoList()
        }
        else {
            throw NoInternetException()
        }
    }


}