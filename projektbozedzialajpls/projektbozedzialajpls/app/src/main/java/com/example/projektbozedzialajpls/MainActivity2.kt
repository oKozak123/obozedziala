package com.example.projektbozedzialajpls
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)
        supportActionBar?.hide()

        val sharedPreferences = getSharedPreferences("ListaLudzi", MODE_PRIVATE)
        val people = getPeopleFromPreferences(sharedPreferences)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PersonAdapter(people) { person ->
            removePerson(person, sharedPreferences)
        }
    }


    private fun getPeopleFromPreferences(sharedPreferences: SharedPreferences): MutableList<ludzik> {
        val json = sharedPreferences.getString("people", "[]")
        val type = object : TypeToken<MutableList<ludzik>>() {}.type
        return Gson().fromJson(json, type)
    }


    private fun removePerson(person: ludzik, sharedPreferences: SharedPreferences) {
        val people = getPeopleFromPreferences(sharedPreferences)
        people.remove(person)
        sharedPreferences.edit().putString("people", Gson().toJson(people)).apply()
        recreate()
    }

}