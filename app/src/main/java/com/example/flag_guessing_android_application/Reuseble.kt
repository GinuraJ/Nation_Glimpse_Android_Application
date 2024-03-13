package com.example.flag_guessing_android_application

import android.content.Context
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class Reusable {

    fun readJson(context: Context, countryMap: MutableMap<String, String>) {
        // Read JSON from assets
        try {
            val json: String = context.assets.open("Countries.json").bufferedReader().use{it.readText() }

            // Parse JSON using kotlinx.serialization
            val countryDataList = Json.decodeFromString<Map<String, String>>(json)

            // Update the countryMap
            countryMap.putAll(countryDataList)
        } catch (e: Exception) {
            // Handle exceptions (e.g., JSON parsing errors)
            // You may want to log the exception or show an error message
            e.printStackTrace()
        }
    }
}