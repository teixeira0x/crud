package com.teixeira.usercrud.domain.usecase.user

import com.teixeira.usercrud.domain.model.user.User
import com.teixeira.usercrud.domain.repository.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllUsersUseCase
@Inject
constructor(private val repository: UserRepository) {

  operator fun invoke(): Flow<List<User>> {
    return repository.getAll()
  }
}
