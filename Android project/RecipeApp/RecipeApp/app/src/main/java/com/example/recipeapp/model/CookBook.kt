package com.example.recipeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class CookBook (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String,
)