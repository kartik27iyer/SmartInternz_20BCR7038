package com.example.recipeapp.model

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)