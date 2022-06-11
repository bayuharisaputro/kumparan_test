package com.example.kumparan_test

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kumparan_test.adapter.CommentListAdapter
import com.example.kumparan_test.di.component.BaseApp
import com.example.kumparan_test.dialog.ErrorDialog
import com.example.kumparan_test.model.UserResponse
import com.example.kumparan_test.viewModel.DetailPostViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.rv_data
import kotlinx.android.synthetic.main.activity_detail.sr_layout
import kotlinx.android.synthetic.main.activity_detail.toolbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {
    private lateinit var mAdapter: CommentListAdapter
    private lateinit var user: UserResponse
    private var mErrorDialog: ErrorDialog? = null
    @Inject
    lateinit var detailPostViewModelFactory: ViewModelProvider.Factory
    private val detailPostVM by viewModels<DetailPostViewModel> { detailPostViewModelFactory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        (application as BaseApp).appComponent.detailPostTaskComponent().create().inject(this)

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
        supportActionBar?.title = "Detail Post"

        mErrorDialog = ErrorDialog(this)

        txt_user.paintFlags = Paint.UNDERLINE_TEXT_FLAG

        intent.extras?.let {
            user = Gson().fromJson(it.getString("user"), UserResponse::class.java)
            txt_title.text = it.getString("postTitle")
            txt_user.text = user.username
            txt_body.text = it.getString("postBody")
        }
        mAdapter = CommentListAdapter(arrayListOf())


        rv_data.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
            itemAnimator = null
        }
    }

    private fun initData() {
        val postId = intent.getIntExtra("postId", 0).toString()
        detailPostVM.requestGetCommentList(postId)
    }

    private fun initEvent() {
        sr_layout.setOnRefreshListener {
            initData()
        }

        detailPostVM.ldCommentList.observe(this, Observer {
            it?.let {
                mAdapter.listComment = it
                mAdapter.notifyDataSetChanged()
            }
        })

        detailPostVM.ldIsLoading.observe(this, Observer {
            sr_layout.isRefreshing = it
        })

        detailPostVM.ldErrorHandler.observe(this, Observer {
            it?.let {
                mErrorDialog?.setDialogData("Get Data Error", it.message.toString())
                mErrorDialog?.show()
            }
        })

        txt_user.setOnClickListener {
            val intent = Intent(this@DetailActivity, UserActivity::class.java)
            intent.putExtra("user", Gson().toJson(user))
            startActivity(intent)
        }
    }
}