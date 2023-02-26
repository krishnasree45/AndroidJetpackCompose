package com.example.emptycomposeactivity.model.service

import com.example.emptycomposeactivity.model.User
import kotlinx.coroutines.flow.Flow

interface LoginService {
    val currentUserId: String
    val hasUser: Boolean

    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String)
    suspend fun createAnonymousAccount()
    suspend fun linkAccount(email: String, password: String)
    suspend fun deleteAccount()
    suspend fun signOut()
}