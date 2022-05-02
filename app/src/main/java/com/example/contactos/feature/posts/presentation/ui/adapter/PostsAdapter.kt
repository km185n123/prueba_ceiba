package com.example.contactos.feature.posts.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactos.data.posts.entities.PostEntity
import com.example.contactos.databinding.ItemPostBinding
import com.example.contactos.feature.posts.presentation.viewmodel.ItemPostViewModel

class PostsAdapter(
    private var list: List<PostEntity>,
) : RecyclerView.Adapter<PostsAdapter.ViewHolder>(){

    private lateinit var _itemBinding: ItemPostBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        _itemBinding = ItemPostBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(_itemBinding,list)
    }
    override fun getItemCount(): Int {
        return list.size
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemPostViewModel = ItemPostViewModel()
        itemPostViewModel.post.value = list[position]
        holder.bind(list[position])
    }

    class ViewHolder(private var tv: ItemPostBinding, var list: List<PostEntity>) : RecyclerView.ViewHolder(tv.root) {
        fun bind(post: PostEntity){
            tv.txtTitle.text = post.title
            tv.txtBody.text = post.body
        }
    }
}