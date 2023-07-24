package com.example.a357project

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.Menu)
        val sPref = applicationContext.getSharedPreferences("MyPref", 0)
        val editor: SharedPreferences.Editor = sPref.edit()
        if (!(sPref.contains("currentStreak"))){
            editor.putInt("currentStreak", 0)
            editor.putInt("maxStreak", 0)
            editor.apply()
        }
        if (!(sPref.contains("currentDifficulty"))){
            editor.putInt("currentDifficulty", 0)
            editor.apply()
        }



        val playButton = findViewById<Button>(R.id.playButton)
        val statsButton = findViewById<Button>(R.id.statsButton)


        playButton.setOnClickListener {
            val i = Intent(this, QuestionActivity::class.java)
            startActivity(i)
        }

        statsButton.setOnClickListener {
            val i = Intent(this, StatsActivity::class.java)
            startActivity(i)
        }

        /* Storage write permissions required to save results on older API levels.
           This requests said permissions only if on older API levels. Could probably
           be done elsewhere but I saw something suggesting it should be done here? */
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            val check = ContextCompat.checkSelfPermission(this, Manifest.permission.
                WRITE_EXTERNAL_STORAGE)
            if (check != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    0)
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?):Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings_action) {
            val intent = Intent(this@MainActivity, SettingsActivity::class.java)
            startActivity(intent)
            return true
        }
        if (item.itemId == R.id.HTP_action) {
            val intent = Intent(this@MainActivity, HowToPlayActivity::class.java)
            startActivity(intent)
            return true
        }
        return false
    }
}