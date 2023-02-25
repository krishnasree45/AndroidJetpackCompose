package com.example.emptycomposeactivity.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId

data class WellnessTask(

    @DocumentId
    val id: String="",

    val label: String = "",

    val checked: Boolean = false,

    val description: String = ""
){

}