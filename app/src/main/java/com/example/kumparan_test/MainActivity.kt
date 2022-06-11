package com.example.kumparan_test

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kumparan_test.adapter.PostListAdapter
import com.example.kumparan_test.di.component.BaseApp
import com.example.kumparan_test.dialog.ErrorDialog
import com.example.kumparan_test.viewModel.PostListViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter: PostListAdapter
    private var mErrorDialog: ErrorDialog? = null
    @Inject
    lateinit var postListViewModelFactory: ViewModelProvider.Factory
    private val postListVM by viewModels<PostListViewModel> { postListViewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as BaseApp).appComponent.postListTaskComponent().create().inject(this)
        initView()
        initEvent()
        initData()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mErrorDialog?.isShowing == true) {
            mErrorDialog?.dismiss()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initView(){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "Home"

        mErrorDialog = ErrorDialog(this)

        mAdapter = PostListAdapter(arrayListOf())
        mAdapter.itemActionListener = object : PostListAdapter.OnItemAction{
            override fun onGetDetailPost(data: PostListViewModel.AlterPost) {
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("postTitle", data.title)
                intent.putExtra("postBody", data.body)
                intent.putExtra("postId", data.id)
                intent.putExtra("user", Gson().toJson(data.user))
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
        postListVM.requestGetPostList()
    }

    private fun initEvent() {
        sr_layout.setOnRefreshListener {
            postListVM.requestGetPostList()
        }

        postListVM.ldPostList.observe(this, Observer {
            it?.let {
                mAdapter.listPost = it
                mAdapter.notifyDataSetChanged()
            }
        })

        postListVM.ldIsLoading.observe(this, Observer {
            sr_layout.isRefreshing = it
        })

        postListVM.ldErrorHandler.observe(this, Observer {
            it?.let {
                mErrorDialog?.setDialogData("Get Data Error", it.message.toString())
                mErrorDialog?.show()
            }
        })
    }
}