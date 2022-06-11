package com.example.kumparan_test.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kumparan_test.model.CommentResponse
import java.util.ArrayList

abstract class DetailPostViewModel : ViewModel() {

    abstract val ldIsLoading: LiveData<Boolean>

    abstract val ldErrorHandler: LiveData<Exception?>

    abstract val ldCommentList: LiveData<ArrayList<CommentResponse>?>

    abstract fun requestGetCommentList(postId: String)

}