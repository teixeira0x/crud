package com.teixeira.usercrud.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
  tableName = "users",
  indices =
    [
      Index(value = arrayOf("username"), unique = true),
      Index(value = arrayOf("email"), unique = true)
    ]
)
class UserEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val username: String,
  val name: String,
  val email: String,
  val password: String
) {
  class Update(
    val id: Long,
    val username: String,
    val name: String,
    val password: String
  )
}
