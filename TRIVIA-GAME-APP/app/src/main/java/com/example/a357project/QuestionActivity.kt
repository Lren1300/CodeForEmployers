package com.example.a357project

import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.BufferedReader
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.util.*


class QuestionActivity : AppCompatActivity() {
    var backPressed: Boolean = false

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        //shared preferences initialization
        val sPref: SharedPreferences = applicationContext.getSharedPreferences("MyPref", 0)
        title = getString(R.string.Question) + (sPref.getInt("currentStreak", 0) + 1)
        val editor: SharedPreferences.Editor = sPref.edit()



        // Initializes used questions list if uncreated, if created loads it into array.
        var used: Array<String> = emptyArray()
        val files: Array<String> = applicationContext.fileList()
        if (!files.contains("used")) {
            applicationContext.openFileOutput("used", Context.MODE_PRIVATE).use {
                it.write("".toByteArray())
            }
        } else {
            val reader: BufferedReader = applicationContext.openFileInput("used").bufferedReader()
            used = reader.readLines().toTypedArray()
            reader.close()
        }

        val categoryLabel = findViewById<TextView>(R.id.category)
        val questionLabel = findViewById<TextView>(R.id.question)
        val answerAButton = findViewById<Button>(R.id.answerA)
        var aIsClicked: Boolean = false
        val answerBButton = findViewById<Button>(R.id.answerB)
        var bIsClicked: Boolean = false
        val answerCButton = findViewById<Button>(R.id.answerC)
        var cIsClicked: Boolean = false
        val answerDButton = findViewById<Button>(R.id.answerD)
        var dIsClicked: Boolean = false
        val progressBar = findViewById<ProgressBar>(R.id.progressBar) as ProgressBar
        var progressStatus = 0
        var difficulty: Int = sPref.getInt("currentDifficulty", 0)
        var counter = 0


        if(difficulty == 0) {
            Thread(Runnable {
                while (progressStatus < 100) {
                    try {
                        counter = counter+1
                        Thread.sleep(200)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    progressStatus = counter
                    progressBar.progress = progressStatus
                    if(aIsClicked || bIsClicked || cIsClicked || dIsClicked || backPressed) {
                        break
                    }
                }
                if(progressBar.progress == progressBar.max){
                    val i = Intent(this, WrongAnswerActivity::class.java)
                    startActivity(i)
                }
            }).start()
        }
        if(difficulty == 1){
            Thread(Runnable {
                while (progressStatus < 100) {
                    try {
                        counter = counter+1
                        Thread.sleep(100)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    progressStatus = counter
                    progressBar.progress = progressStatus
                    if(aIsClicked || bIsClicked || cIsClicked || dIsClicked || backPressed) {
                        break
                    }
                }
                if(progressBar.progress == progressBar.max){
                    val i = Intent(this, WrongAnswerActivity::class.java)
                    startActivity(i)
                }
            }).start()
        }
        if(difficulty == 2){
            Thread(Runnable {
                while (progressStatus < 100) {
                    try {
                        counter = counter+1
                        Thread.sleep(50)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    progressStatus = counter
                    progressBar.progress = progressStatus
                    if(aIsClicked || bIsClicked || cIsClicked || dIsClicked || backPressed) {
                        break
                    }
                }
                if(progressBar.progress == progressBar.max){
                    val i = Intent(this, WrongAnswerActivity::class.java)
                    startActivity(i)
                }
            }).start()
        }



        var currentQuestion = Random().nextInt(1998) + 10000

        var unusedQuestion = false

        while (!unusedQuestion) {
            /* If used list is empty, write question to used list. This is a separate
               condition so that the first line is not a newline character, which can
               mess with the count. Probably a better way of doing this but who really
               cares if it works, eh? */
            if (used.isEmpty()) {
                applicationContext.openFileOutput("used", Context.MODE_APPEND).use {
                    it.write(currentQuestion.toString().toByteArray())
                }
                unusedQuestion = true
            /* If question is not in used list, question is unused. Write question
               to used list, then flag the question as unused to exit the loop. */
            } else if (!used.contains(currentQuestion.toString())) {
                applicationContext.openFileOutput("used", Context.MODE_APPEND).use {
                    it.write(("\n"+(currentQuestion.toString())).toByteArray())
                }
                unusedQuestion = true

            /* If the question has been used and there are still unused questions
               remaining, add one to the current value, then loop again. Modular
               division to ensure that we loop from 11998 back to 10000. Find a
               more efficient way to do this. Hashing algorithms from 263 maybe
               helpful? */
            } else if (used.size < 1999) {
                currentQuestion = (((currentQuestion-10000)+1)%1999)+10000

            /* If we've already used the question but we have no more unused questions,
               blank the used questions file, blank the array, then loop again to write
               as used. */
            } else {
                applicationContext.openFileOutput("used", Context.MODE_PRIVATE).use {
                    it.write("".toByteArray())
                }
                used = emptyArray()
            }
        }

        val currentQuestionString: String = "Question_" + currentQuestion
        val currentA: String = "A_"+currentQuestion
        val currentB: String = "B_"+currentQuestion
        val currentC: String = "C_"+currentQuestion
        val currentD: String = "D_"+currentQuestion

        val currQ: String = getString(applicationContext.resources.getIdentifier(
            currentQuestionString, "string", packageName))
        val currA : String = getString(applicationContext.resources.getIdentifier(
            currentA, "string", packageName))
        val currB : String = getString(applicationContext.resources.getIdentifier(
            currentB, "string", packageName))
        val currC : String = getString(applicationContext.resources.getIdentifier(
            currentC, "string", packageName))
        val currD : String = getString(applicationContext.resources.getIdentifier(
            currentD, "string", packageName))

        questionLabel.text = currQ
        answerAButton.text = currA
        answerBButton.text = currB
        answerCButton.text = currC
        answerDButton.text = currD

        val currentCategory = "Category_$currentQuestion"
        val currentCategoryStr: String = getString(applicationContext.resources.getIdentifier(
            currentCategory, "string", packageName))
        categoryLabel.text = getString(R.string.Category) + ": " + currentCategoryStr

        val correctAnswerString = "Answer_$currentQuestion"
        val correctAnswer: String = getString(applicationContext.resources.getIdentifier(
            correctAnswerString, "string", packageName))

        var currentStreak = sPref.getInt("currentStreak", 0)

        answerAButton.setOnClickListener{
            aIsClicked = true
            if(correctAnswer == "A" ){
                //updating the current streak
                currentStreak += 1
                editor.putInt("currentStreak", currentStreak)
                editor.apply()
                val i = Intent(this, CorrectAnswerActivity::class.java)
                startActivity(i)
            }
            else {
                val i = Intent(this, WrongAnswerActivity::class.java)
                startActivity(i)
            }
            finish()
        }

        answerBButton.setOnClickListener{
            bIsClicked = true
            if(correctAnswer == "B" ){
                currentStreak += 1
                editor.putInt("currentStreak", currentStreak)
                editor.apply()
                val i = Intent(this, CorrectAnswerActivity::class.java)
                startActivity(i)
            }
            else {
                val i = Intent(this, WrongAnswerActivity::class.java)
                startActivity(i)
            }
            finish()

        }

        answerCButton.setOnClickListener{
            cIsClicked = true
            if(correctAnswer == "C" ){
                currentStreak += 1
                editor.putInt("currentStreak", currentStreak)
                editor.apply()
                val i = Intent(this, CorrectAnswerActivity::class.java)
                startActivity(i)
            }
            else {
                val i = Intent(this, WrongAnswerActivity::class.java)
                startActivity(i)
            }
            finish()
        }

        answerDButton.setOnClickListener{
            dIsClicked = true
            if(correctAnswer == "D" ){
                currentStreak += 1
                editor.putInt("currentStreak", currentStreak)
                editor.apply()
                val i = Intent(this, CorrectAnswerActivity::class.java)
                startActivity(i)
            }
            else {
                val i = Intent(this, WrongAnswerActivity::class.java)
                startActivity(i)
            }
            finish()

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        backPressed = true
    }

}