package com.example.kumparan_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kumparan_test.adapter.AlbumListAdapter
import com.example.kumparan_test.di.component.BaseApp
import com.example.kumparan_test.dialog.ErrorDialog
import com.example.kumparan_test.model.PhotoResponse
import com.example.kumparan_test.model.UserResponse
import com.example.kumparan_test.viewModel.UserDetailViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.activity_user.rv_data
import kotlinx.android.synthetic.main.activity_user.sr_layout
import kotlinx.android.synthetic.main.activity_user.toolbar
import javax.inject.Inject

class UserActivity : AppCompatActivity() {
    private lateinit var mAdapter: AlbumListAdapter
    private lateinit var user: UserResponse
    private var mErrorDialog: ErrorDialog? = null
    @Inject
    lateinit var userDetailViewModelFactory: ViewModelProvider.Factory
    private val userDetailVM by viewModels<UserDetailViewModel> { userDetailViewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        (application as BaseApp).appComponent.userDetailTaskComponent().create().inject(this)
        initView()
        initEvent()
        initData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mErrorDialog?.isShowing == true) {
            mErrorDialog?.dismiss()
        }
    }

    private fun initView(){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Detail User"

        mErrorDialog = ErrorDialog(this)

        intent.extras?.let {
            user = Gson().fromJson(it.getString("user"), UserResponse::class.java)
            txt_username.text = "${user.username} [${user.email}]"
            txt_address.text = "Address : ${user.address?.street}, ${user.address?.suite}, ${user.address?.city}, ${user.address?.zipcode} "
            txt_company.text = "Company : ${user.company?.name} (${user.company?.bs})"
        }
        mAdapter = AlbumListAdapter(arrayListOf())
        mAdapter.itemActionListener = object : AlbumListAdapter.OnItemAction {
            override fun onGetPhotoClicked(data: PhotoResponse) {
                val intent = Intent(this@UserActivity, DetailPhotoActivity::class.java)
                intent.putExtra("photo", Gson().toJson(data))
                startActivity(intent)
            }

            override fun onViewAllClicked(data: ArrayList<PhotoResponse>) {
                val intent = Intent(this@UserActivity, ViewAllPhotosActivity::class.java)
                intent.putExtra("photos", Gson().toJson(data))
                startActivity(intent)
            }
        }

        rv_data.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
            itemAnimator = null
        }
    }

    private fun initData() {
        userDetailVM.requestGetAlbumList(user.id.toString())
    }

    private fun initEvent() {
        sr_layout.setOnRefreshListener {
            userDetailVM.requestGetAlbumList(user.id.toString())
        }

        userDetailVM.ldAlbumList.observe(this, Observer {
            it?.let {
                mAdapter.listAlbum = it
                mAdapter.notifyDataSetChanged()
            }
        })

        userDetailVM.ldIsLoading.observe(this, Observer {
            sr_layout.isRefreshing = it
        })

        userDetailVM.ldErrorHandler.observe(this, Observer {
            it?.let {
                mErrorDialog?.setDialogData("Get Data Error", it.message.toString())
                mErrorDialog?.show()
            }
        })
    }

}