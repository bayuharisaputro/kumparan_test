package com.example.kumparan_test.model

import com.google.gson.annotations.SerializedName


data class AlbumResponse (

  @SerializedName("userId" ) var userId : String?    = null,
  @SerializedName("id"     ) var id     : Int?    = null,
  @SerializedName("title"   ) var title   : String? = null

)