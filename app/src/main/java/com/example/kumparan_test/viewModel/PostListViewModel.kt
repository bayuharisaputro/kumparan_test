package com.example.kumparan_test.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kumparan_test.model.UserResponse
import java.util.ArrayList

abstract class PostListViewModel : ViewModel() {

    data class AlterPost(var id: Int, var title: String,
                         var body: String, var user: UserResponse)

    abstract val ldIsLoading: LiveData<Boolean>

    abstract val ldErrorHandler: LiveData<Exception?>

    abstract val ldPostList: LiveData<ArrayList<AlterPost>?>

    abstract fun requestGetPostList()

}