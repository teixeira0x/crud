package com.teixeira.usercrud.domain.usecase.user

import com.teixeira.usercrud.domain.model.user.User
import com.teixeira.usercrud.domain.repository.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetUserUseCase
@Inject
constructor(private val repository: UserRepository) {

  operator fun invoke(id: Long): Flow<User?> {
    return repository.findUserById(id)
  }
}
