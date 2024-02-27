package com.teixeira.usercrud.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.teixeira.usercrud.R
import com.teixeira.usercrud.adapters.UserListAdapter
import com.teixeira.usercrud.databinding.FragmentUserListBinding
import com.teixeira.usercrud.domain.model.user.User
import com.teixeira.usercrud.ui.userdetails.UserDetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserListFragment : Fragment() {

  private val viewModel by viewModels<UserListViewModel>()

  private var _binding: FragmentUserListBinding? = null
  private val binding: FragmentUserListBinding
    get() = checkNotNull(_binding) { "Fragment has been destroyed!" }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentUserListBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    populateUserList()
    setListeners()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun populateUserList() {
    viewModel.allUsers.asLiveData().observe(viewLifecycleOwner) { users ->
      binding.rvUsers.adapter =
        UserListAdapter(
          users = users,
          onMenuClick = this@UserListFragment::showUserItemMenu
        )
    }
  }

  private fun setListeners() {
    binding.fabAddUser.setOnClickListener {
      findNavController().navigate(R.id.fragmentUser)
    }
  }

  private fun showUserItemMenu(view: View, user: User) {
    PopupMenu(requireContext(), view).apply {
      menu.add(0, 0, 0, R.string.update)
      menu.add(0, 1, 0, R.string.remove)

      setOnMenuItemClickListener { item ->
        when (item.itemId) {
          0 -> {
            findNavController()
              .navigate(
                R.id.fragmentUser,
                Bundle().apply {
                  putLong(UserDetailsFragment.KEY_USER_ID, user.id)
                }
              )
          }
          1 -> viewModel.removeUser(user.id)
        }
        true
      }
      show()
    }
  }
}
