package com.example.cardgame1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HelpActivity : AppCompatActivity() {

    lateinit var button : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        button = findViewById(R.id.button_back3)
        button.setOnClickListener {

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

        }
    }
}