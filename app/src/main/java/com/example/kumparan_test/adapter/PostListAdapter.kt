package com.example.kumparan_test.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kumparan_test.R
import com.example.kumparan_test.viewModel.PostListViewModel
import kotlinx.android.synthetic.main.item_post.view.*
import java.util.ArrayList


class PostListAdapter(var listPost: ArrayList<PostListViewModel.AlterPost>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemAction {
        fun onGetDetailPost(data : PostListViewModel.AlterPost)
    }

    var itemActionListener: OnItemAction? = null

    override fun getItemCount(): Int = listPost.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HolderContent(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? BindHolder)?.bind(position)
    }

    inner class HolderContent(itemView: View) : RecyclerView.ViewHolder(itemView), BindHolder {
        private val mTitle = itemView.txt_title
        private val mBody = itemView.txt_body
        private val mUser = itemView.txt_user
        private val mCompany = itemView.txt_company



        init {
            itemView.setOnClickListener {
                if(adapterPosition != -1) {
                    itemActionListener?.onGetDetailPost(listPost[adapterPosition])
                }
            }
        }

        override fun bind(pos: Int) {
            val data = listPost[pos]
            mTitle.text = data.title
            mBody.text = data.body
            mUser.text = data.user.username
            mCompany.text =  "@${data.user?.company?.name}"
        }
    }
}