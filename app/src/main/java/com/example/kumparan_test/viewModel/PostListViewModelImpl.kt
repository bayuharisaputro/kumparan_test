package com.example.kumparan_test.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kumparan_test.repository.PostListRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class PostListViewModelImpl @Inject constructor(
        private val repository: PostListRepository
) : PostListViewModel() {

    private val mIsLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val mErrorHandler: MutableLiveData<Exception?> = MutableLiveData()
    private val mPostList: MutableLiveData<ArrayList<AlterPost>?> = MutableLiveData()

    override val ldIsLoading: LiveData<Boolean> = mIsLoading
    override val ldErrorHandler: LiveData<Exception?> = mErrorHandler
    override val ldPostList: LiveData<java.util.ArrayList<AlterPost>?> = mPostList

    override fun requestGetPostList() {
        viewModelScope.launch {
            mIsLoading.value = true
            try {
                val rawPost = repository.getPostList()
                val rawUser = repository.getUserList()
                val alterData: ArrayList<AlterPost> = arrayListOf()
                rawPost?.forEach {post->
                    val user = rawUser?.find { post.userId == it.id }
                    alterData.add(AlterPost(post.id?:0, post.title.toString(),post.body.toString(), user?:return@launch))
                }

                mPostList.value = alterData
                mErrorHandler.value = null
            }
            catch (e: Exception) {
                mPostList.value = null
                mErrorHandler.value = e
            }
            finally {
                mIsLoading.value = false
            }
        }
    }

}