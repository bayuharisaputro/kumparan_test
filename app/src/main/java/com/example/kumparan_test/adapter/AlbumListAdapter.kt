package com.example.kumparan_test.adapter


import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.kumparan_test.R
import com.example.kumparan_test.model.PhotoResponse
import com.example.kumparan_test.viewModel.UserDetailViewModel
import kotlinx.android.synthetic.main.item_album.view.*
import kotlin.collections.ArrayList


class AlbumListAdapter(var listAlbum: ArrayList<UserDetailViewModel.AlterAlbum>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemAction {
        fun onGetPhotoClicked(data : PhotoResponse)
        fun onViewAllClicked(data : ArrayList<PhotoResponse>)
    }


    var itemActionListener: OnItemAction? = null

    override fun getItemCount(): Int = listAlbum.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HolderContent(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? BindHolder)?.bind(position)
    }

    inner class HolderContent(itemView: View) : RecyclerView.ViewHolder(itemView), BindHolder {
        private val mAlbumName = itemView.txt_album_name
        private val mImageList = itemView.image_slider
        private val mViewAll = itemView.txt_view_all


        init {
            mViewAll.paintFlags = Paint.UNDERLINE_TEXT_FLAG

            mViewAll.setOnClickListener {
                if(adapterPosition != -1)
                itemActionListener?.onViewAllClicked(listAlbum[adapterPosition].photoList)
            }

        }

        override fun bind(pos: Int) {
            val data = listAlbum[pos]
            val imageList = ArrayList<SlideModel>()
            //only display 5 photos for showcases
            data.photoList.subList(0,5).forEach {
                imageList.add(SlideModel(it.thumbnailUrl, it.title))
            }
            mAlbumName.text = data.albumResponse.title
            mImageList.setImageList(imageList, ScaleTypes.FIT)
            mImageList.setItemClickListener(object : ItemClickListener {
                override fun onItemSelected(position: Int) {
                    itemActionListener?.onGetPhotoClicked(listAlbum[pos].photoList[position])
                }
            })
        }
    }
}