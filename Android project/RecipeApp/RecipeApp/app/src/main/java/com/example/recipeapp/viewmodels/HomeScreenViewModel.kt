package com.example.recipeapp.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.model.Recipe
import com.example.recipeapp.model.RecipeX
import com.example.recipeapp.utils.Constants.API_KEY
import com.example.recipeapp.utils.Resource
import com.example.remote.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    var isLoading = mutableStateOf(false)
    var recipeList = mutableStateOf<Recipe>(Recipe(listOf()))
    var indianRecipeList = mutableStateOf(Recipe(listOf()))
    var veganRecipeList = mutableStateOf(Recipe(listOf()))

    init {
        loadRecipes()
        loadDesertRecipes()
        loadVeganRecipes()
    }

    fun loadVeganRecipes(){
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getRecipeByTags(API_KEY, "10","vegan")

            when(result){
                is Resource.Success -> {
                    veganRecipeList.value = result.data!!
                    Log.d("api_response","success")
                    isLoading.value = false

                }
                is Resource.Error -> {
                    isLoading.value = false
                }


                else -> {

                }
            }


        }
    }
    fun loadDesertRecipes(){
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getRecipeByTags(API_KEY, "10","indian")

            when(result){
                is Resource.Success -> {
                    indianRecipeList.value = result.data!!
                    Log.d("api_response","success")
                    isLoading.value = false

                }
                is Resource.Error -> {
                    isLoading.value = false
                }


                else -> {

                }
            }


        }
    }
    fun loadRecipes() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getRandomRecipe(API_KEY, "10")

            when(result){
                is Resource.Success -> {
                    recipeList.value = result.data!!
                    Log.d("api_response","success")
                    isLoading.value = false

                }
                is Resource.Error -> {
                    isLoading.value = false
                }


                else -> {

                }
            }
        }

    }
}