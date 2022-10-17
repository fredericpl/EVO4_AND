package com.example.evo4_and.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Type(
    @PrimaryKey(autoGenerate = true)
    var typeId: Long = 0,
    var nameType: String
        )