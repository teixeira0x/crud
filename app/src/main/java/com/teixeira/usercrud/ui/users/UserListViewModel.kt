package com.teixeira.usercrud.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teixeira.usercrud.domain.model.user.User
import com.teixeira.usercrud.domain.usecase.user.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@HiltViewModel
class UserListViewModel
@Inject
constructor(
  private val getAllUsers: GetAllUsersUseCase,
  private val removeUser: RemoveUserUseCase,
) : ViewModel() {

  val allUsers: Flow<List<User>>
    get() = getAllUsers()

  fun removeUser(id: Long) = viewModelScope.launch { removeUser.invoke(id) }
}
