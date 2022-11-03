package com.xten.starf.white

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xten.starf.R
import com.xten.starf.databinding.ActivityStartBinding

class Start : AppCompatActivity() {
    private lateinit var bindStart: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindStart = ActivityStartBinding.inflate(layoutInflater)
        setContentView(bindStart.root)

        bindStart.startBtn.setOnClickListener{
            startActivity(Intent(this, Game::class.java))
        }
    }
}