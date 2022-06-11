package com.example.kumparan_test.repository

import com.example.kumparan_test.model.AlbumResponse
import com.example.kumparan_test.model.PhotoResponse

interface UserDetailRepository {
    suspend fun getAlbumList(userId: String): ArrayList<AlbumResponse>?
    suspend fun getPhotoList(): ArrayList<PhotoResponse>?
}