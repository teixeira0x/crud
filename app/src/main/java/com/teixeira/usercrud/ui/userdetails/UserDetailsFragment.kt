package com.teixeira.usercrud.ui.userdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.teixeira.usercrud.R
import com.teixeira.usercrud.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {
  private val viewModel by viewModels<UserDetailsViewModel>()

  private var _binding: FragmentUserBinding? = null
  private val binding: FragmentUserBinding
    get() = checkNotNull(_binding) { "Fragment has been destroyed!" }

  private var userId: Long = 0

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentUserBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    arguments?.let { userId = it.getLong(KEY_USER_ID) }

    setViewModelObservers()
    configureUserDetails()
    setListeners()
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun setViewModelObservers() {
    viewModel.event.observe(viewLifecycleOwner) { event ->
      when (event) {
        is UserDetailsViewModel.Event.Message -> {
          Snackbar.make(requireView(), event.message, Snackbar.LENGTH_SHORT)
            .show()
        }
        is UserDetailsViewModel.Event.UserInserted,
        is UserDetailsViewModel.Event.UserUpdated -> {
          findNavController().popBackStack()
        }
      }
    }
  }

  private fun setListeners() {
    binding.btnSave.setOnClickListener {
      val username = binding.edUsername.text.toString().trim()
      val name = binding.edFullname.text.toString().trim()
      val email = binding.edEmail.text.toString().trim()
      val password = binding.edPass.text.toString().trim()

      viewModel.sendAction(
        if (userId > 0) {
          UserDetailsViewModel.Action.UpdateUserAction(
            id = userId,
            username = username,
            name = name,
            password = password
          )
        } else {
          UserDetailsViewModel.Action.InsertUserAction(
            username = username,
            name = name,
            email = email,
            password = password
          )
        }
      )
    }
  }

  private fun configureUserDetails() {
    if (userId > 0) {
      viewModel.findUser(userId).filterNotNull().asLiveData().observe(
        viewLifecycleOwner
      ) { user ->
        binding.edUsername.setText(user.username)
        binding.edFullname.setText(user.name)
        binding.edPass.setText(user.password)
      }
      binding.btnSave.text = getString(R.string.update)
      binding.inputEmail.visibility = View.GONE
    } else {
      binding.btnSave.text = getString(R.string.add)
      binding.inputEmail.visibility = View.VISIBLE
    }
  }

  companion object {
    const val KEY_USER_ID: String = "arg_userId"
  }
}
