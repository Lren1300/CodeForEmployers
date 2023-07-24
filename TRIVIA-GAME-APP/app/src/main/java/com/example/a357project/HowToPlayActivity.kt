package com.example.a357project

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HowToPlayActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_to_play)

        title = getString(R.string.How_To_Play)

        val nextButton = findViewById<Button>(R.id.nextButton)

        nextButton.setOnClickListener{
            val i = Intent(this, HowToPlay2Activity::class.java)
            startActivity(i)
            finish()
        }
    }
}