package com.example.projektbozedzialajpls

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val imieInput: TextInputEditText = findViewById(R.id.inp_imie)
        val nazwiskoInput: TextInputEditText = findViewById(R.id.inp_nazwisko)
        val wiekInput: EditText = findViewById(R.id.inp_wiek)
        val wzrostInput: EditText = findViewById(R.id.inp_wzrost)
        val wagaInput: EditText = findViewById(R.id.inp_waga)

        val zapiszButton: Button = findViewById(R.id.btn_zapisz)
        val ekran2Button: Button = findViewById(R.id.btn_ekran_2)

        val sharedPreference = getSharedPreferences("ListaLudzi", MODE_PRIVATE)

        zapiszButton.setOnClickListener {
            savePerson(imieInput, nazwiskoInput, wiekInput, wzrostInput, wagaInput, sharedPreference)
        }

        ekran2Button.setOnClickListener {
            val ekran2 = Intent(applicationContext, MainActivity2::class.java)
            startActivity(ekran2)
        }
    }

    private fun savePerson(
        imieInput: TextInputEditText,
        nazwiskoInput: TextInputEditText,
        wiekInput: EditText,
        wzrostInput: EditText,
        wagaInput: EditText,
        sharedPreference: SharedPreferences
    ) {
        val imie = imieInput.text.toString()
        val nazwisko = nazwiskoInput.text.toString()
        val wiek = wiekInput.text.toString().toIntOrNull()
        val wzrost = wzrostInput.text.toString().toIntOrNull()
        val waga = wagaInput.text.toString().toIntOrNull()

        when {
            imie.isEmpty() -> showToast("Imię nie może być puste")
            nazwisko.isEmpty() -> showToast("Nazwisko nie może być puste")
            wiek == null || wiek <= 0 -> showToast("Wiek musi być liczbą dodatnią większą niż 0")
            wzrost == null || wzrost !in 50..250 -> showToast("Wzrost musi być w przedziale 50–250 cm")
            waga == null || waga !in 3..200 -> showToast("Waga musi być w przedziale 3–200 kg")
            else -> {
                val person = Person(imie, nazwisko, wiek, wzrost, waga)
                val people = getPeopleFromPreferences(sharedPreference)
                people.add(person)
                sharedPreference.edit().putString("people", Gson().toJson(people)).apply()
                showToast("Dane zapisane pomyślnie")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    private fun getPeopleFromPreferences(sharedPreferences: SharedPreferences): MutableList<Person> {
        val json = sharedPreferences.getString("people", "[]")
        val type = object : TypeToken<MutableList<Person>>() {}.type
        return Gson().fromJson(json, type)
    }
}