package com.teixeira.usercrud.domain.usecase.user

import com.teixeira.usercrud.domain.model.user.*
import com.teixeira.usercrud.domain.repository.UserRepository
import javax.inject.Inject

class UpdateUserUseCase
@Inject
constructor(private val repository: UserRepository) {

  suspend operator fun invoke(params: Params) {
    repository.updateUser(
      with(params) {
        UserUpdate(
          id = id,
          username = username,
          name = name,
          password = password
        )
      }
    )
  }

  class Params(
    val id: Long,
    val username: String,
    val name: String,
    val password: String
  )
}
