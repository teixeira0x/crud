package com.teixeira.usercrud.domain.repository

import com.teixeira.usercrud.domain.model.user.User
import com.teixeira.usercrud.domain.model.user.UserInsert
import com.teixeira.usercrud.domain.model.user.UserUpdate
import kotlinx.coroutines.flow.Flow

interface UserRepository {

  fun getAll(): Flow<List<User>>

  fun findUserById(id: Long): Flow<User?>

  suspend fun insertUser(user: UserInsert): Long

  suspend fun updateUser(user: UserUpdate)

  suspend fun removeUser(id: Long)
}
