package com.example.emptycomposeactivity.model.service

import android.util.Log
import com.example.emptycomposeactivity.model.User
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginServiceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : LoginService{
    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener = FirebaseAuth.AuthStateListener { auth ->
                this.trySend(auth.currentUser?.let { User(it.uid, it.isAnonymous) } ?: User())
            }
            firebaseAuth.addAuthStateListener(listener)
            awaitClose { firebaseAuth.removeAuthStateListener(listener) }
        }

    override val currentUserId: String
        get() = firebaseAuth.currentUser?.uid.orEmpty()

    override val hasUser: Boolean
        get() = firebaseAuth.currentUser != null

    override suspend fun authenticate(email: String, password: String) {
        Log.d("LoginViewModel", "authenticate")
        firebaseAuth.signInWithEmailAndPassword(email, password)
    }

    override suspend fun createAnonymousAccount() {
        firebaseAuth.signInAnonymously().await()
    }

    override suspend fun linkAccount(email: String, password: String) {
        val credential = EmailAuthProvider.getCredential(email, password)
        firebaseAuth.currentUser!!.linkWithCredential(credential).await()
    }

    override suspend fun signOut() {
        if(firebaseAuth.currentUser!!.isAnonymous){
            firebaseAuth.currentUser!!.delete()
        } else {
            firebaseAuth.signOut()
        }
        firebaseAuth.signInAnonymously().await()

    }

    override suspend fun deleteAccount() {
        Log.d("LoginServiceImpl", "deleteAccount Before")
        firebaseAuth.currentUser!!.delete().await()
        Log.d("LoginServiceImpl", "deleteAccount After")
    }

}