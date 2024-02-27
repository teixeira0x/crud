package com.teixeira.usercrud.di

import android.content.Context
import androidx.room.Room
import com.teixeira.usercrud.database.UserDatabase
import com.teixeira.usercrud.domain.repository.UserRepository
import com.teixeira.usercrud.domain.repository.impl.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DatabaseModule {

  @Binds
  fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

  companion object {

    @Provides
    @Singleton
    fun provideDatabase(
      @ApplicationContext applicationContext: Context
    ): UserDatabase {
      return Room.databaseBuilder(
          context = applicationContext,
          klass = UserDatabase::class.java,
          name = "user-database"
        )
        .build()
    }
  }
}
