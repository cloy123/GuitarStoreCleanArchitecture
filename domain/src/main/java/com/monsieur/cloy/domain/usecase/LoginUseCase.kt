package com.monsieur.cloy.domain.usecase

import com.monsieur.cloy.domain.common.LoginResult
import com.monsieur.cloy.domain.models.BasketItem
import com.monsieur.cloy.domain.repository.BasketItemRepository
import com.monsieur.cloy.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class LoginUseCase (private val userRepository: UserRepository) {
    suspend fun execute(login: String, password: String): Flow<LoginResult> {
        return flow {
            val user = userRepository.findUser(login, password)
            if(user == null){
                emit(LoginResult(false, null))
            }else{
                emit(LoginResult(true, user))
            }
        }
    }
}