package com.example.a357project

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HowToPlay2Activity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how_to_play2)

        title = getString(R.string.How_To_Play)

        val homeButton = findViewById<Button>(R.id.h2pHome)
        val backButton = findViewById<Button>(R.id.h2pBackButton)

        homeButton.setOnClickListener{
            finish()
        }

        backButton.setOnClickListener{
            val i = Intent(this, HowToPlayActivity::class.java)
            startActivity(i)
            finish()
        }


    }
}