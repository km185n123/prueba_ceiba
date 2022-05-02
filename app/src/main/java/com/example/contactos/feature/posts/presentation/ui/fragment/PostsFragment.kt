package com.example.contactos.feature.posts.presentation.ui.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactos.R
import com.example.contactos.data.posts.entities.PostEntity
import com.example.contactos.databinding.PostsFragmentBinding
import com.example.contactos.feature.posts.presentation.ui.adapter.PostsAdapter
import com.example.contactos.feature.posts.presentation.viewmodel.PostsViewModelImp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostsFragment : Fragment() {

    private lateinit var _binding: PostsFragmentBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val postsViewModel = ViewModelProvider(this).get(PostsViewModelImp::class.java)
        _binding = PostsFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        activity?.setTitle(R.string.app_name)

        val userId = arguments?.getString("userId")
        val name = arguments?.getString("name")
        val email = arguments?.getString("email")
        val phone = arguments?.getString("phone")

        name.let { _binding.txtName.text = it }
        email.let { _binding.txtEmail.text = it }
        phone.let { _binding.txtPhone.text = it }
        userId.let {
            if (it != null) {
                postsViewModel.fetchPosts(it)
            }
        }

        val dialog = ProgressDialog.show(
            requireActivity(), "",
            "Loading. Please wait...", true
        )
        postsViewModel.progressDialogLiveData.observe(viewLifecycleOwner) {isShow->
            if (isShow){
                dialog.show()
            }else{
                dialog.hide()
            }
        }

        postsViewModel.postsLiveData.observe(viewLifecycleOwner, this::loadUsers)
        postsViewModel.showErrorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(),it, Toast.LENGTH_LONG).show()
        }
        return root
    }

    private fun loadUsers(list: List<PostEntity>?) {
        _binding.postsRecycler.layoutManager = LinearLayoutManager(activity)
        _binding.postsRecycler.adapter =list?.let { PostsAdapter(it) }
    }






}