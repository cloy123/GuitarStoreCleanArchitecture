package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.common.RegisterUserResult
import com.monsieur.cloy.domain.models.User
import com.monsieur.cloy.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RegisterUserUseCase(private val userRepository: UserRepository) {
    suspend fun execute(user: User): Flow<RegisterUserResult>{
        return flow {
            if(userRepository.isUserExist(user.login)){
                emit(RegisterUserResult(false, null))
            }else{
                val newUser = userRepository.insertUser(user)
                emit(RegisterUserResult(true, newUser))
            }
        }
    }
}