package com.example.kumparan_test.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kumparan_test.model.PhotoResponse
import com.example.kumparan_test.repository.UserDetailRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class UserDetailViewModelImpl @Inject constructor(
        private val repository: UserDetailRepository
) : UserDetailViewModel() {

    private val mIsLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val mErrorHandler: MutableLiveData<Exception?> = MutableLiveData()
    private val mAlbumList: MutableLiveData<ArrayList<AlterAlbum>?> = MutableLiveData()

    override val ldIsLoading: LiveData<Boolean> = mIsLoading
    override val ldErrorHandler: LiveData<Exception?> = mErrorHandler
    override val ldAlbumList: LiveData<java.util.ArrayList<AlterAlbum>?> = mAlbumList

    override fun requestGetAlbumList(userId: String) {
        viewModelScope.launch {
            mIsLoading.value = true
            try {
                val dataAlbum = repository.getAlbumList(userId)
                val dataPhoto = repository.getPhotoList()
                val alterAlbum: ArrayList<AlterAlbum> = arrayListOf()
                dataAlbum?.forEach { album->
                    alterAlbum.add(AlterAlbum(album, (dataPhoto?.filter { it.albumId == album.id } as ArrayList<PhotoResponse> )))
                }
                mAlbumList.value = alterAlbum
                mErrorHandler.value = null
            }
            catch (e: Exception) {
                mAlbumList.value = null
                mErrorHandler.value = e
            }
            finally {
                mIsLoading.value = false
            }
        }
    }

}