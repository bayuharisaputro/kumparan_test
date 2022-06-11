package com.example.kumparan_test.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kumparan_test.R
import com.example.kumparan_test.model.CommentResponse
import kotlinx.android.synthetic.main.item_comment.view.*
import java.util.ArrayList


class CommentListAdapter(var listComment: ArrayList<CommentResponse>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemCount(): Int = listComment.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HolderContent(
                        LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? BindHolder)?.bind(position)
    }

    inner class HolderContent(itemView: View) : RecyclerView.ViewHolder(itemView), BindHolder {
        private val mBody = itemView.txt_body
        private val mAuthor = itemView.txt_author


        override fun bind(pos: Int) {
            val data = listComment[pos]
            mBody.text = data.body
            mAuthor.text = data.email
        }
    }
}