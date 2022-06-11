package com.example.kumparan_test.datastore

import com.example.kumparan_test.model.PostResponse
import com.example.kumparan_test.model.UserResponse


interface PostListRemoteDataStore {
    suspend fun getPostList(): ArrayList<PostResponse>?
    suspend fun getUserList(): ArrayList<UserResponse>?
}