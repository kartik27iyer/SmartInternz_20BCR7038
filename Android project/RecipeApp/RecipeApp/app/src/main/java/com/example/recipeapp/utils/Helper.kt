package com.example.recipeapp.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URLEncoder

fun String.urlEncode(): String = URLEncoder.encode(this, "utf-8")

inline fun <reified T> Gson.frommJson(json: String) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)