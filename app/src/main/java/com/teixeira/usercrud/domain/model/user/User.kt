package com.teixeira.usercrud.domain.model.user

class User(
  val id: Long,
  val username: String,
  val name: String,
  val email: String,
  val password: String
) {
  val displayName: String
    get() = "$name ($id)"
}
