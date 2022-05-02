package com.example.contactos.feature.users.presentation.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.contactos.R
import com.example.contactos.data.users.entities.UserEntity
import com.example.contactos.databinding.ItemUserBinding
import com.example.contactos.feature.users.presentation.viewmodel.ItemUserViewModel

class UsersAdapter(
    private var list: List<UserEntity>,
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>(){

    private lateinit var _itemBinding: ItemUserBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        _itemBinding = ItemUserBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(_itemBinding,list)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemUserViewModel = ItemUserViewModel()
        itemUserViewModel.user.value = list[position]
        holder.bind(list[position])
    }

    class ViewHolder(private var tv: ItemUserBinding, var list: List<UserEntity>) : RecyclerView.ViewHolder(tv.root) {
        fun bind(user: UserEntity){
            tv.txtName.text = user.name
            tv.txtPhone.text = user.phone
            tv.txtEmail.text = user.email
            tv.btnMore.setOnClickListener {
                val arg = Bundle()
                arg.putString("userId",user.id)
                arg.putString("name",user.name)
                arg.putString("email",user.email)
                arg.putString("phone",user.phone)
                it.findNavController().navigate(R.id.action_usersFragment_to_postsFragment2,arg)
            }
        }
    }
}