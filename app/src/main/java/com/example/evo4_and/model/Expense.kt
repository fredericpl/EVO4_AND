package com.example.evo4_and.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Expense (
    @PrimaryKey(autoGenerate = true)
    var expenseId : Long = 0,
    var name : String,
    var value : Float,
    var date: String? = null,
    @Embedded
    val type: Type? = Type(nameType = "random")
        )