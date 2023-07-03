package com.example.room

import androidx.room.TypeConverter
import com.example.recipeapp.model.AnalyzedInstruction
import com.example.recipeapp.model.ExtendedIngredientX
import com.example.recipeapp.model.WinePairing
import com.example.recipeapp.utils.frommJson
import com.google.gson.Gson



class Converters {

    @TypeConverter
    fun fromAnalyzedInstruction(value : List<AnalyzedInstruction>):String{
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toAnalyzedInstructions(value : String): List<AnalyzedInstruction>{
         return  try {
             Gson().frommJson<List<AnalyzedInstruction>>(value)
         }catch (e : java.lang.Exception){
             listOf()
         }
    }

    @TypeConverter
    fun fromExtendedIngredients(value : List<ExtendedIngredientX>):String{
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toExtendedIngredients(value : String): List<ExtendedIngredientX>{
        return  try {
            Gson().frommJson<List<ExtendedIngredientX>>(value)
        }catch (e : java.lang.Exception){
            listOf()
        }
    }

    @TypeConverter
    fun fromWinePairing(value : List<WinePairing>) : String{
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toWinePairing(value : String) : WinePairing{
        return  try {
            Gson().frommJson<WinePairing>(value)
        }catch (e : java.lang.Exception) {
            return  WinePairing(listOf(),"", listOf())
        }
    }
    @TypeConverter
    fun fromCuisines(value : List<String>):String{
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toCuisines(value : String): List<String>{
        return  try {
            Gson().frommJson<List<String>>(value)
        }catch (e : java.lang.Exception){
            listOf()
        }
    }
}