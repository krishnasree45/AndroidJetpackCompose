package com.example.emptycomposeactivity.model.service

import android.util.Log
import com.example.emptycomposeactivity.model.WellnessTask
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.asDeferred
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject
constructor(private val firestore: FirebaseFirestore, private val auth: LoginService) :
    StorageService {

    override val tasks: Flow<List<WellnessTask>>
        get() =
            auth.currentUser.flatMapLatest { user ->
                currentCollection(user.id).snapshots().map { snapshot -> snapshot.toObjects() }
            }

    override suspend fun getTask(taskId: String): WellnessTask? =
        currentCollection(auth.currentUserId).document().get().await().toObject()

    override suspend fun save(task: WellnessTask) {
        currentCollection(auth.currentUserId).add(task)
    }

    override suspend fun update(task: WellnessTask): Unit {
        currentCollection(auth.currentUserId).document(task.id).set(task).await()
    }

    override suspend fun delete(taskId: String) {
        currentCollection(auth.currentUserId).document(taskId).delete().await()
    }

    override suspend fun deleteAllForUser(userId: String) {
        Log.d("StorageServiceImpl", "deleteAllForUser Before")
        val matchingTasks = currentCollection(userId).get().await()
        matchingTasks.map { it.reference.delete().asDeferred() }.awaitAll()
        Log.d("StorageServiceImpl", "deleteAllForUser After")
    }

    private fun currentCollection(uid: String): CollectionReference =
        firestore.collection(USER_COLLECTION).document(uid).collection(TASK_COLLECTION)

    companion object {
        private const val USER_COLLECTION = "users"
        private const val TASK_COLLECTION = "tasks"
    }
}