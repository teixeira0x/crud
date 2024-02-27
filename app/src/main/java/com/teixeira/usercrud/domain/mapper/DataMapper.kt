package com.teixeira.usercrud.domain.mapper

import com.teixeira.usercrud.database.entity.UserEntity
import com.teixeira.usercrud.domain.model.user.*

object DataMapper {

  fun UserEntity.toModel(): User {
    return User(
      id = id,
      username = username,
      name = name,
      email = email,
      password = password
    )
  }

  fun User.toEntity(): UserEntity {
    return UserEntity(
      id = id,
      username = username,
      name = name,
      email = email,
      password = password
    )
  }

  fun UserInsert.toEntity(): UserEntity {
    return UserEntity(
      username = username,
      name = name,
      email = email,
      password = password
    )
  }

  fun UserUpdate.toEntity(): UserEntity.Update {
    return UserEntity.Update(
      id = id,
      username = username,
      name = name,
      password = password
    )
  }
}
