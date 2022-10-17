package com.example.evo4_and.model

import androidx.room.Embedded
import androidx.room.Relation


data class ExpenseWithType (
    @Embedded val expense: Expense,
    @Relation(
        parentColumn = "expenseId",
        entityColumn = "typeId"
    )
    val type: Type
        )