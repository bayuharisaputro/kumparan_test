package com.example.kumparan_test.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kumparan_test.model.AlbumResponse
import com.example.kumparan_test.model.PhotoResponse
import kotlin.collections.ArrayList

abstract class UserDetailViewModel : ViewModel() {
    data class AlterAlbum(val albumResponse: AlbumResponse, val photoList: ArrayList<PhotoResponse>)
    abstract val ldIsLoading: LiveData<Boolean>

    abstract val ldErrorHandler: LiveData<Exception?>

    abstract val ldAlbumList: LiveData<ArrayList<AlterAlbum>?>

    abstract fun requestGetAlbumList(userId: String)

}