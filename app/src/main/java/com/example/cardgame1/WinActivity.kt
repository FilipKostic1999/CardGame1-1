package com.example.cardgame1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class WinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)

        lateinit var button : Button

        button = findViewById(R.id.button_0)


        val shared = getSharedPreferences("Score", MODE_PRIVATE)
        val Score = shared.getInt("Score", 0)

        Log.d("!!!", "score:  $Score" )


        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}