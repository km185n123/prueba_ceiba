package com.example.contactos.feature.users.presentation.ui.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contactos.R
import com.example.contactos.data.users.entities.UserEntity
import com.example.contactos.databinding.UsersFragmentBinding
import com.example.contactos.feature.users.presentation.ui.adapter.UsersAdapter
import com.example.contactos.feature.users.presentation.viewmodel.UsersViewModelImp
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UsersFragment : Fragment() {

   private lateinit var _binding: UsersFragmentBinding
    private val binding get() = _binding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val usersViewModel = ViewModelProvider(this).get(UsersViewModelImp::class.java)
        _binding = UsersFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        activity?.setTitle(R.string.app_name)
        usersViewModel.fetchUsers(null)
        val dialog = ProgressDialog.show(
            requireActivity(), "",
            "Loading. Please wait...", true
        )
        usersViewModel.usersLiveData.observe(viewLifecycleOwner) {
            loadUsers(it)
        }
        usersViewModel.showErrorLiveData.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
        }
        usersViewModel.listIsEmptyLiveData.observe(viewLifecycleOwner) {
            _binding.emptyMessage.visibility = it
        }
        usersViewModel.progressDialogLiveData.observe(viewLifecycleOwner) {isShow->
             if (isShow){
                 dialog.show()
             }else{
                 dialog.hide()
             }
        }
        _binding.txtFilter.addTextChangedListener {textKey->
            usersViewModel.fetchUsers(textKey.toString())
        }
        return root
    }

    private fun loadUsers(list: List<UserEntity>?) {
        _binding.usersRecycler.layoutManager = LinearLayoutManager(activity)
        _binding.usersRecycler.adapter =list?.let { UsersAdapter(it) }
    }





}