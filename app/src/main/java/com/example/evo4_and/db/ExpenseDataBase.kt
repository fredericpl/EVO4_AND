package com.example.evo4_and.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.evo4_and.dao.ExpenseDao
import com.example.evo4_and.dao.TypeDao
import com.example.evo4_and.model.Expense
import com.example.evo4_and.model.Type

@Database(entities = arrayOf(Expense::class, Type::class), version = 1)
abstract class ExpenseDatabase : RoomDatabase() {
    abstract fun ExpenseDao(): ExpenseDao
    abstract fun TypeDao(): TypeDao
}