package com.example.a357project

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.documentfile.provider.DocumentFile
import java.io.File


class StatsActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)
        title = "Stats"
        val sPref = applicationContext.getSharedPreferences("MyPref", 0)
        val statHomeButton = findViewById<Button>(R.id.stats2home)
        val streakValue = findViewById<TextView>(R.id.streakValue)
        val longestStreak = findViewById<TextView>(R.id.highestValue)
        val shareButton = findViewById<Button>(R.id.shareButton)
        val myStreak = sPref.getInt("currentStreak", 0)
        val bestStreak = sPref.getInt("maxStreak", 0)
        streakValue.text = "$myStreak"
        longestStreak.text = "$bestStreak"

        statHomeButton.setOnClickListener {
            finish()
        }

        shareButton.setOnClickListener {
            /* Two different methods of checking if the file exists, since
               for some reason this produces two different results on the
               different systems. */
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val uri = sPref.getString("recentURI", "none")
                val sourceFile = DocumentFile.fromSingleUri(this, Uri.parse(uri))

                val exists = sourceFile!!.exists()

                if (uri != "none" && exists) {
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(uri)
                    startActivity(i)
                } else {
                    Toast.makeText(this, getString(R.string.Save_Not_Found), Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                try {
                    val path = sPref.getString("recentPath", "none")
                    val sourceFile = DocumentFile.fromFile(File(path))
                    val uri = sPref.getString("recentURI", "none")

                    val exists = sourceFile!!.exists()

                    if (uri != "none" && exists) {
                        val i = Intent(Intent.ACTION_VIEW)
                        i.data = Uri.parse(uri)
                        startActivity(i)
                    } else {
                        Toast.makeText(this, getString(R.string.Save_Not_Found), Toast.LENGTH_SHORT)
                            .show()
                    }
                } catch (exception : Exception) {
                    Toast.makeText(this, "Save not found.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?):Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settings_action) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            return true
        }
        if (item.itemId == R.id.HTP_action) {
            val intent = Intent(this, HowToPlayActivity::class.java)
            startActivity(intent)
            return true
        }
        return false
    }


}