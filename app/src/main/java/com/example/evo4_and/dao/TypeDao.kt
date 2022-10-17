package com.example.evo4_and.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.evo4_and.model.Type

@Dao
interface TypeDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(type: Type)

    @Query("SELECT * FROM type")
    fun getAll(): List<Type>

    @Query("SELECT * FROM Type")
    fun getAllLive(): LiveData<List<Type>>


}