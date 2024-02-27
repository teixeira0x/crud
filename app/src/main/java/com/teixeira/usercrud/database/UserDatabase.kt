package com.teixeira.usercrud.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.teixeira.usercrud.database.dao.UserDao
import com.teixeira.usercrud.database.entity.UserEntity

@Database(entities = arrayOf(UserEntity::class), version = 1)
abstract class UserDatabase : RoomDatabase() {
  abstract val userDao: UserDao
}
