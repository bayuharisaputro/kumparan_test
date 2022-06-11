package com.example.kumparan_test

import android.graphics.Color
import android.os.Bundle
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.kumparan_test.model.PhotoResponse
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_photo.*
import kotlinx.android.synthetic.main.activity_detail_photo.toolbar
import javax.inject.Inject


class DetailPhotoActivity : AppCompatActivity() {
    private lateinit var photos: PhotoResponse

    @Inject
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_photo)
        initView()
        initEvent()
        initData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        intent.extras?.let {
            photos = Gson().fromJson(it.getString("photo"), PhotoResponse::class.java)
            supportActionBar?.title = photos.title
            //using simple webview to handle zoom in zoom out full image because the url basically html
            web_view.webViewClient = WebViewClient()
            web_view.loadUrl(photos.url.toString())

            web_view.settings.javaScriptEnabled = true

            web_view.settings.setSupportZoom(true)
            web_view.settings.builtInZoomControls = true
            web_view.settings.displayZoomControls = false
            web_view.settings.useWideViewPort = true
            web_view.settings.loadWithOverviewMode = true
        }
    }

    private fun initData() {}

    private fun initEvent() {
        val snackBar = Snackbar.make(
            ll_parent, "Pinch to Zoom Image",
            Snackbar.LENGTH_LONG
        ).setAction("Action", null)
        snackBar.setActionTextColor(Color.WHITE)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(Color.LTGRAY)
        val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.WHITE)
        snackBar.show()
    }

}