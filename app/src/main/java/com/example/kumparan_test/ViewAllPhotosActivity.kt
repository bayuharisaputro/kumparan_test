package com.example.kumparan_test

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.kumparan_test.model.PhotoResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_album.*
import javax.inject.Inject

class ViewAllPhotosActivity : AppCompatActivity() {
    private lateinit var photos: ArrayList<PhotoResponse>
    @Inject
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_all_photos)
        initView()
        initEvent()
        initData()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun initView(){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = "View All Images"

        intent.extras?.let {
            photos = Gson().fromJson(it.getString("photos"),
                object : TypeToken<ArrayList<PhotoResponse?>?>() {}.type
            )
        }
        val imageList = ArrayList<SlideModel>()
        photos.forEach {
            imageList.add(SlideModel(it.thumbnailUrl, it.title))
        }
        image_slider.setImageList(imageList, ScaleTypes.CENTER_INSIDE)
    }

    private fun initData() {}

    private fun initEvent() {
        image_slider.setItemClickListener(object : ItemClickListener {
            override fun onItemSelected(position: Int) {
                val intent = Intent(this@ViewAllPhotosActivity, DetailPhotoActivity::class.java)
                intent.putExtra("photo", Gson().toJson(photos[position]))
                startActivity(intent)
            }
        })
    }
}