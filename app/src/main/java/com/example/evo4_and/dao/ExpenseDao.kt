package com.example.evo4_and.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.evo4_and.model.Expense

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM Expense")
    fun getAll (): LiveData<List<Expense>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(expense: Expense)

}