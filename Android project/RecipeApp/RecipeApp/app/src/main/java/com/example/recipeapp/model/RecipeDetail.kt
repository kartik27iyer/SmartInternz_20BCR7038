package com.example.recipeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeDetail(
    val aggregateLikes: Int=0,
    val analyzedInstructions: List<AnalyzedInstruction> = listOf(),
    val cheap: Boolean = false,
    val cookingMinutes: Int = 0,
    val creditsText: String = "",
    val cuisines: List<String> = listOf(),
    val dairyFree: Boolean = true,
    val diets: List<String> = listOf(),
    val dishTypes: List<String> = listOf(),
    val extendedIngredients: List<ExtendedIngredientX> = listOf(),
    val gaps: String = "",
    val glutenFree: Boolean = false,
    val healthScore: Int = 0,
    @PrimaryKey
    val id: Int = 0,
    val image: String = "",
    val imageType: String = "",
    val instructions: String = "",
    val license: String = "",
    val lowFodmap: Boolean = false,
    val occasions: List<String> = listOf(),
    val originalId: Int = 0,
    val preparationMinutes: Int = 0,
    val pricePerServing: Double = 0.0,
    val readyInMinutes: Int = 0,
    val servings: Int = 0,
    val sourceName: String = "",
    val sourceUrl: String = "",
    val spoonacularSourceUrl: String = "",
    val summary: String = "",
    val sustainable: Boolean = true,
    val title: String = "",
    val vegan: Boolean = true,
    val vegetarian: Boolean = true,
    val veryHealthy: Boolean = true,
    val veryPopular: Boolean = true,
    val weightWatcherSmartPoints: Int = 0,
    val cookBook : String = "")