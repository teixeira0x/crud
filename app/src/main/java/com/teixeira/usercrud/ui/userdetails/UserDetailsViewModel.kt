package com.teixeira.usercrud.ui.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teixeira.usercrud.R
import com.teixeira.usercrud.domain.model.user.User
import com.teixeira.usercrud.domain.usecase.user.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@HiltViewModel
class UserDetailsViewModel
@Inject
constructor(
  private val getAllUsers: GetAllUsersUseCase,
  private val getUser: GetUserUseCase,
  private val insertUser: InsertUserUseCase,
  private val removeUser: RemoveUserUseCase,
  private val updateUser: UpdateUserUseCase
) : ViewModel() {
  private val _event = MutableLiveData<Event>()

  val event: LiveData<Event>
    get() = _event

  fun findUser(id: Long): Flow<User?> = getUser(id)

  fun sendAction(action: Action) {
    when (action) {
      is Action.InsertUserAction -> insertUserAction(action)
      is Action.UpdateUserAction -> updateUserAction(action)
    }
  }

  private fun insertUserAction(action: Action.InsertUserAction) {
    viewModelScope.launch {
      try {
        insertUser(
          with(action) {
            InsertUserUseCase.Params(
              username = username,
              name = name,
              email = email,
              password = password
            )
          }
        )

        sendEvent(Event.Message(R.string.user_inserted))
        sendEvent(Event.UserInserted())
      } catch (e: Exception) {
        sendEvent(Event.Message(R.string.user_error_to_insert))
      }
    }
  }

  private fun updateUserAction(action: Action.UpdateUserAction) {
    viewModelScope.launch {
      try {
        updateUser(
          with(action) {
            UpdateUserUseCase.Params(
              id = id,
              username = username,
              name = name,
              password = password
            )
          }
        )

        sendEvent(Event.Message(R.string.user_updated))
        sendEvent(Event.UserUpdated())
      } catch (e: Exception) {
        sendEvent(Event.Message(R.string.user_error_to_update))
      }
    }
  }

  private fun sendEvent(event: Event) {
    _event.value = event
  }

  sealed interface Action {
    class InsertUserAction(
      val username: String,
      val name: String,
      val email: String,
      val password: String
    ) : Action

    class UpdateUserAction(
      val id: Long,
      val username: String,
      val name: String,
      val password: String
    ) : Action
  }

  sealed interface Event {
    class Message(val message: Int) : Event

    class UserInserted : Event

    class UserUpdated : Event
  }
}
