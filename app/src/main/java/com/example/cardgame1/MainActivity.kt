package com.example.cardgame1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.VideoView
import androidx.core.content.ContextCompat.startActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
//  ydytfdyf
    fun openNextActivity1(view: View) {
        val intent = Intent(this, SingelpActivity::class.java)
        startActivity(intent)
    }

    fun openNextActivity2(view: View) {
        val intent = Intent(this, MultiplayerActivity::class.java)
        startActivity(intent)
    }

    fun openNextActivity3(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun openNextActivity4(view: View) {
        val intent = Intent(this, StatsActivity::class.java)
        startActivity(intent)
    }

    fun openNextActivity5(view: View) {
        val intent = Intent(this, HelpActivity::class.java)
        startActivity(intent)

    }
}