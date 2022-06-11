package com.example.kumparan_test.datastore

import com.example.kumparan_test.model.AlbumResponse
import com.example.kumparan_test.model.PhotoResponse


interface UserDetailRemoteDataStore {
    suspend fun getAlbumList(userId: String): ArrayList<AlbumResponse>?
    suspend fun getPhotoList(): ArrayList<PhotoResponse>?
}