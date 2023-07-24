package com.example.a357project

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        title = getString(R.string.Settings)
        val sPref = applicationContext.getSharedPreferences("MyPref", 0)
        val editor: SharedPreferences.Editor = sPref.edit()

        val difficultySpinner = findViewById<Spinner>(R.id.diffSpinner)
        val saveButton = findViewById<FloatingActionButton>(R.id.fab)

        ArrayAdapter.createFromResource(
            this,
            R.array.difficultyArray,
            android.R.layout.simple_spinner_item
        ).also {adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            difficultySpinner.adapter = adapter
        }

        var currentDifficulty = sPref.getInt("currentDifficulty", 0)
        difficultySpinner.setSelection(currentDifficulty)

        saveButton.setOnClickListener {
            editor.putInt("currentDifficulty", difficultySpinner.selectedItemPosition)
            editor.apply()
            this.finish()
        }

    }
}