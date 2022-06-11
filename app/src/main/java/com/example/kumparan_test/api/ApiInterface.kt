package com.example.kumparan_test.api

import com.example.kumparan_test.model.*
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {

    @GET("posts")
    fun getPostList(): Call<ArrayList<PostResponse>>

    @GET("users")
    fun getUserList(): Call<ArrayList<UserResponse>>

    @GET("comments")
    fun getCommentList(@Query("postId") postId: String): Call<ArrayList<CommentResponse>>

    @GET("users/{userId}/albums")
    fun getUserAlbums(@Path("userId") userId: String): Call<ArrayList<AlbumResponse>>

    @GET("photos")
    fun getPhotoList(): Call<ArrayList<PhotoResponse>>
}