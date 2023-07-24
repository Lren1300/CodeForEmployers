package com.example.a357project

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.*
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import java.io.File
import java.io.FileOutputStream
import java.util.*

class WrongAnswerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wrong_answer)
        val sPref = applicationContext.getSharedPreferences("MyPref", 0)
        val editor: SharedPreferences.Editor = sPref.edit()
        val tryAgainButton = findViewById<Button>(R.id.tryAgainButton)
        val stopButton = findViewById<Button>(R.id.wHome)
        val saveScoreButton = findViewById<Button>(R.id.saveButton)
        val finalStreak = findViewById<TextView>(R.id.wcurrentStreak)
        val longestStreak = findViewById<TextView>(R.id.wLongestStreak)

        val finalStreakSaved = sPref.getInt("currentStreak", 0)
        val longestStreakSaved = sPref.getInt("maxStreak", 0)

        title = getString(R.string.Question) + (finalStreakSaved + 1)

        finalStreak.text = getString(R.string.Final_Streak) + finalStreakSaved
        longestStreak.text = getString(R.string.Longest_Streak) + " " + longestStreakSaved
        editor.putInt("currentStreak", 0)
        editor.apply()

        saveScoreButton.setOnClickListener {
            generateImage(finalStreakSaved, longestStreakSaved, sPref, editor)
        }

        tryAgainButton.setOnClickListener {
            val i = Intent(this, QuestionActivity::class.java)
            startActivity(i)
            finish()
        }
        stopButton.setOnClickListener {

            /*val i = Intent(this, MainActivity::class.java )
            startActivity(i)*/
            finish()

        }
    }

    private fun generateImage(finalStreakSaved: Int, longestStreakSaved: Int, sPref: SharedPreferences,
        editor: SharedPreferences.Editor) {
        /* Image generation code sourced from StackOverflow thread here:
           https://stackoverflow.com/questions/9124896/generate-a-image-with-custom-text-in-android
         */
        var source = BitmapFactory.decodeResource(resources, R.drawable.endcard)
        var saved: Bitmap =
            Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val hour = cal.get(Calendar.HOUR_OF_DAY)
        val minute = cal.get(Calendar.MINUTE)
        val second = cal.get(Calendar.SECOND)
        val diffNum = sPref.getInt("currentDifficulty", 0)
        val diff : String
        if (diffNum == 1) {
            diff = getString(R.string.Medium)
        } else if (diffNum == 2) {
            diff = getString(R.string.Hard)
        } else {
            diff = getString(R.string.Easy)
        }
        val imgHeader =  getString(R.string.Stats_For_Today) + " $year-$month-$day:"
        val streakText = getString(R.string.Today_Streak) + " " + finalStreakSaved
        val bestText =  getString(R.string.Longest_Streak) + " " + longestStreakSaved
        val timerOn = getString(R.string.Difficulty) + " " + diff


        var canvas = Canvas(saved)
        var paint = Paint()
        paint.textSize = 120F
        paint.color = Color.GREEN
        paint.style = Paint.Style.FILL
        canvas.drawBitmap(source, 0f, 0F, null)
        val height = paint.measureText("yY") + 1100F
        val width = paint.measureText(imgHeader)
        val x = (source.width - width) / 2
        canvas.drawText(imgHeader, x, height, paint)
        canvas.drawText(streakText, x, height + 150F, paint)
        canvas.drawText(bestText, x, height + 300F, paint)
        canvas.drawText(timerOn, x, height + 450F, paint)

        /* Now, save using MediaStore API if on Android 10+.*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            try {
                val resolver = contentResolver
                val values = ContentValues()
                values.put(MediaStore.MediaColumns.DISPLAY_NAME, "TriviaStats_$year$month$day" +
                        "_" + "$hour$minute$second.jpg")
                values.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                values.put(
                    MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES +
                            File.separator + "357Project"
                )

                val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
                val outputStream = Objects.requireNonNull(uri)?.let { resolver.openOutputStream(it) }
                Thread {
                    saved.compress(
                        Bitmap.CompressFormat.JPEG, 100,
                        outputStream
                    )
                    /* This must be done within the thread, else things turn out
                       empty which is unideal. */
                    outputStream?.flush()
                    outputStream?.close()
                }.start()
                /* This is definitely not how this is supposed to work, but I'm unsure why I
                   can't get the full path to write so I'm just concatenating this on myself. */
                editor.putString("recentURI", "content://media/" + uri?.path.toString())
                editor.apply()


                Toast.makeText(this, getString(R.string.Saved), Toast.LENGTH_SHORT).show()
            } catch (exception: Exception) {
                Toast.makeText(this, getString(R.string.Failed_To_Save), Toast.LENGTH_SHORT).show()
            }
        } else {
            /* Alternative method for older APIs. */
            val root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                .toString() + "/357Project"
            val directory = File(root)
            directory.mkdirs()
            val file = File(directory, "TriviaStats_$year$month$day" +
                    "_" + "$hour$minute$second.jpg")
            /* Possibly unnecessary? Not sure if this method auto-renames files for redundancy
               so I left it in here. */
            if (file.exists()) {
                file.delete()
            }
            try {
                val outputStream = FileOutputStream(file)
                Thread {
                    saved.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    val path = MediaStore.Images.Media.insertImage(this.contentResolver, file.path, file.name, null)
                    editor.putString("recentURI", path.toString())
                    editor.putString("recentPath", file.path.toString())
                    editor.apply()
                    /* Again, make sure this remains in the thread or things get ugly. */
                    outputStream.flush()
                    outputStream.close()
                }.start()
                Toast.makeText(this, getString(R.string.Saved), Toast.LENGTH_SHORT).show()
            } catch (exception: Exception) {
                Toast.makeText(this, getString(R.string.Failed_To_Save), Toast.LENGTH_SHORT).show()
            }

        }
    }
}