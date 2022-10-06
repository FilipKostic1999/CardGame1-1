package com.example.cardgame1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class activity_win2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win2)

        lateinit var button2 : Button

        button2 = findViewById(R.id.button2)

        button2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        val shared2 = getSharedPreferences("Score", MODE_PRIVATE)
        val Score2 = shared2.getInt("Score2", 0)

        Log.d("!!!", "score2:  $Score2" )

    }
}

