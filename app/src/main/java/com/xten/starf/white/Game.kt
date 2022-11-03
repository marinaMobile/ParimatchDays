package com.xten.starf.white

import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.xten.starf.R
import com.xten.starf.databinding.ActivityGameBinding

class Game : AppCompatActivity() {
    private var count1 = 0
    private var isEnd = false
    private var flag = false
    var bally: ImageView? = null
    private val FIRST_GAME = "FIRST_GAME"
    private lateinit var spref: SharedPreferences
    private lateinit var bindGame: ActivityGameBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindGame = ActivityGameBinding.inflate(layoutInflater)
        setContentView(bindGame.root)

        flag = true
        spref = getPreferences(Context.MODE_PRIVATE);



        val s : Long = "10".toLong() * 1000

        object : CountDownTimer( s , 1000) {

            override fun onTick(millisUntilFinished: Long) {
                bindGame.running.text = "${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                MaterialAlertDialogBuilder(this@Game)
                    .setTitle("Time's Up")
                    .setMessage("Your time's over. Do you want to play again?")
                    .setCancelable(false)
                    .setPositiveButton("Play again"){dialog, _ ->
                        val preferences = getSharedPreferences("PREF", 0)
                        val editor = preferences.edit()
                        editor.apply()
                        startActivity(Intent(applicationContext, Game::class.java))
                        finish()
                        dialog.dismiss()
                    }
                    .create()
                    .show()
            }
        }.start()
    }

    fun ball(view: View?) {
        bally = bindGame.ballRed
        if (!isEnd) {
            if (flag && count1 < 27) {
                ObjectAnimator.ofFloat(bally, View.Y, bally!!.y, bally!!.y - 50).setDuration(100)
                    .start()
                count1++
            } else {
                flag = false
                isEnd = true
                if(spref.getBoolean(FIRST_GAME, true)){
                    with(spref.edit()){
                        putBoolean(FIRST_GAME, false)
                        apply()
                    }
                    startActivity(Intent(this, Finally::class.java))
                } else {
                    MaterialAlertDialogBuilder(this@Game)
                        .setTitle("You won!")
                        .setMessage("You won the game! Do you want to play again?")
                        .setCancelable(false)
                        .setPositiveButton("Play again"){dialog, _ ->
                            val preferences = getSharedPreferences("PREF", 0)
                            val editor = preferences.edit()
                            editor.apply()
                            startActivity(Intent(applicationContext, Game::class.java))
                            finish()
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
            }
        }
    }
}