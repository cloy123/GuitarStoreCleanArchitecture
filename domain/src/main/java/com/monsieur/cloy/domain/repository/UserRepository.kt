package com.monsieur.cloy.domain.repository

import com.monsieur.cloy.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun insertUser(user: User): User

    suspend fun updateUser(user: User)

    suspend fun findUser(login: String, password: String): User?

    suspend fun isUserExist(login: String): Boolean
}