package com.teixeira.usercrud.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.teixeira.usercrud.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
  @Query("SELECT * FROM users") fun getAll(): Flow<List<UserEntity>>

  @Query("SELECT * FROM users WHERE id = :id")
  fun findUserById(id: Long): Flow<UserEntity?>

  @Insert suspend fun insert(user: UserEntity): Long

  @Update(entity = UserEntity::class)
  suspend fun update(user: UserEntity.Update)

  @Query("DELETE FROM users WHERE id = :id") suspend fun remove(id: Long)
}
