package com.example.kumparan_test.datastore

import com.example.kumparan_test.model.CommentResponse


interface DetailPostRemoteDataStore {
    suspend fun getComment(postId : String): ArrayList<CommentResponse>?
}