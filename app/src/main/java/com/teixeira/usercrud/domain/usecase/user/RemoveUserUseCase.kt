package com.teixeira.usercrud.domain.usecase.user

import com.teixeira.usercrud.domain.repository.UserRepository
import javax.inject.Inject

class RemoveUserUseCase
@Inject
constructor(private val repository: UserRepository) {

  suspend operator fun invoke(id: Long) {
    repository.removeUser(id)
  }
}
