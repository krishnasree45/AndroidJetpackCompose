package com.example.emptycomposeactivity.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "wellnessTasks")
data class WellnessTask(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "wellnessTaskId")
    var id: Int,

    @ColumnInfo(name = "taskName")
    val label: String,

    @ColumnInfo(name = "checkedValue")
    var checked: Boolean = false,
){
//    var checked: Boolean by mutableStateOf(initialChecked)
}