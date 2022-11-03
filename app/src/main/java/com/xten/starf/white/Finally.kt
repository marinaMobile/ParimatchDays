package com.xten.starf.white

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.xten.starf.R
import com.xten.starf.databinding.ActivityFinallyBinding
import com.xten.starf.databinding.ActivityMainBinding

class Finally : AppCompatActivity() {
    private lateinit var bindFin: ActivityFinallyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindFin = ActivityFinallyBinding.inflate(layoutInflater)
        setContentView(bindFin.root)

        bindFin.againBtn.setOnClickListener{
            startActivity(Intent(this, Game::class.java))
        }
    }
}