package com.example.kumparan_test.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kumparan_test.model.CommentResponse
import com.example.kumparan_test.repository.DetailPostRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class DetailPostViewModelImpl @Inject constructor(
        private val repository: DetailPostRepository
) : DetailPostViewModel() {

    private val mIsLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val mErrorHandler: MutableLiveData<Exception?> = MutableLiveData()
    private val mCommentList: MutableLiveData<ArrayList<CommentResponse>?> = MutableLiveData()

    override val ldIsLoading: LiveData<Boolean> = mIsLoading
    override val ldErrorHandler: LiveData<Exception?> = mErrorHandler
    override val ldCommentList: LiveData<java.util.ArrayList<CommentResponse>?> = mCommentList

    override fun requestGetCommentList(postId: String) {
        viewModelScope.launch {
            mIsLoading.value = true
            try {
                val data = repository.getCommentList(postId)
                mCommentList.value = data
                mErrorHandler.value = null
            }
            catch (e: Exception) {
                mCommentList.value = null
                mErrorHandler.value = e
            }
            finally {
                mIsLoading.value = false
            }
        }
    }


}