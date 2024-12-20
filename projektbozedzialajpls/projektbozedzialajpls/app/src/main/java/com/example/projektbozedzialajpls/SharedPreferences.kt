package com.example.projektbozedzialajpls
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



class SharedPreferences(context: Context) {


    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("PeopleList", Context.MODE_PRIVATE)
    private val gson = Gson()


    fun savePerson(person: ludzik) {
        val editor = sharedPreferences.edit()
        val people = getPeople().toMutableList()
        people.add(person)
        editor.putString("people", gson.toJson(people))
        editor.apply()
    }


    private fun getPeople(): List<ludzik> {
        val json = sharedPreferences.getString("people", "[]")
        val type = object : TypeToken<List<ludzik>>() {}.type
        return gson.fromJson(json, type)
    }


    fun clearPeople() {
        val editor = sharedPreferences.edit()
        editor.remove("people")
        editor.apply()
    }


}