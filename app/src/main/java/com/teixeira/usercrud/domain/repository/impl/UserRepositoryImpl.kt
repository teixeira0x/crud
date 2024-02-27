package com.teixeira.usercrud.domain.repository.impl

import com.teixeira.usercrud.database.UserDatabase
import com.teixeira.usercrud.domain.mapper.DataMapper.toEntity
import com.teixeira.usercrud.domain.mapper.DataMapper.toModel
import com.teixeira.usercrud.domain.model.user.User
import com.teixeira.usercrud.domain.model.user.UserInsert
import com.teixeira.usercrud.domain.model.user.UserUpdate
import com.teixeira.usercrud.domain.repository.UserRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl
@Inject
constructor(private val database: UserDatabase) : UserRepository {
  private val userDao = database.userDao

  override fun getAll(): Flow<List<User>> {
    return userDao.getAll().map { users -> users.map { it.toModel() } }
  }

  override fun findUserById(id: Long): Flow<User?> {
    return userDao.findUserById(id).map { it?.toModel() }
  }

  override suspend fun insertUser(user: UserInsert): Long {
    return userDao.insert(user.toEntity())
  }

  override suspend fun updateUser(user: UserUpdate) {
    userDao.update(user.toEntity())
  }

  override suspend fun removeUser(id: Long) {
    userDao.remove(id)
  }
}
