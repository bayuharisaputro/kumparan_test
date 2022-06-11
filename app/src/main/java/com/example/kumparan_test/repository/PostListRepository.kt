package com.example.kumparan_test.repository

import com.example.kumparan_test.model.PostResponse
import com.example.kumparan_test.model.UserResponse


interface PostListRepository {
    suspend fun getPostList(): ArrayList<PostResponse>?
    suspend fun getUserList(): ArrayList<UserResponse>?
}