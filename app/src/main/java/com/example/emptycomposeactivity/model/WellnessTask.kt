package com.example.emptycomposeactivity.model

import com.google.firebase.firestore.DocumentId

data class WellnessTask(

    @DocumentId
    val id: String = "",

    val label: String = "",

    val checked: Boolean = false,

    val description: String = ""
) {

}