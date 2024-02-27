package com.teixeira.usercrud.domain.usecase.user

import com.teixeira.usercrud.domain.model.user.*
import com.teixeira.usercrud.domain.repository.UserRepository
import javax.inject.Inject

class InsertUserUseCase
@Inject
constructor(private val repository: UserRepository) {

  suspend operator fun invoke(params: Params): Long {
    return repository.insertUser(
      with(params) {
        UserInsert(
          username = username,
          name = name,
          email = email,
          password = password
        )
      }
    )
  }

  class Params(
    val username: String,
    val name: String,
    val email: String,
    val password: String
  )
}
