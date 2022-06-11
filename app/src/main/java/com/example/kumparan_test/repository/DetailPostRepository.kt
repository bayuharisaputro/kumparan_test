package com.example.kumparan_test.repository

import com.example.kumparan_test.model.CommentResponse

interface DetailPostRepository {
    suspend fun getCommentList(postId: String): ArrayList<CommentResponse>?
}