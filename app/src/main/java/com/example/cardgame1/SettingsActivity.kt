package com.example.cardgame1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class SettingsActivity : AppCompatActivity() {



    lateinit var atomBomb: ImageView
    lateinit var deadSkull: ImageView
    lateinit var enableDisable: ImageView
    lateinit var enableDisable2: ImageView

    lateinit var button: Button

    var enableN : Int = 0
    var enableN2 : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        atomBomb = findViewById(R.id.atomBomb)
        deadSkull = findViewById(R.id.deadSkull)

        enableDisable = findViewById(R.id.enableDisable)
        enableDisable2 = findViewById(R.id.enableDisable2)

        val sharedEnableN = getSharedPreferences("EnableN", AppCompatActivity.MODE_PRIVATE)
        val EnableN = sharedEnableN.getInt("EnableN", 0)


        val sharedEnableN2 = getSharedPreferences("EnableN2", AppCompatActivity.MODE_PRIVATE)
        val EnableN2 = sharedEnableN2.getInt("EnableN2", 0)



        if (EnableN == 1) {
            enableDisable.setImageResource(R.drawable.disable)

        } else if (EnableN == 0) {
            enableDisable.setImageResource(R.drawable.enable)

        }


        if (EnableN2 == 1) {
            enableDisable2.setImageResource(R.drawable.disable)

        } else if (EnableN2 == 0) {
            enableDisable2.setImageResource(R.drawable.enable)

        }



        button = findViewById(R.id.button_back2)

        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        enableDisable.setOnClickListener {

            if (enableN == 0) {
                enableDisable.setImageResource(R.drawable.disable)
                enableN ++


            } else if (enableN == 1) {
                enableDisable.setImageResource(R.drawable.enable)
                enableN --
            }





            val sharedEnableN = getSharedPreferences("EnableN", AppCompatActivity.MODE_PRIVATE)

            val edit = sharedEnableN.edit()

            edit.putInt("EnableN", enableN)

            edit.commit()






        }



        enableDisable2.setOnClickListener{

            if (enableN2 == 0) {
                enableDisable2.setImageResource(R.drawable.disable)
                enableN2 ++
            }  else if (enableN2 == 1) {
                enableDisable2.setImageResource(R.drawable.enable)
                enableN2 --
            }





            val sharedEnableN2 = getSharedPreferences("EnableN2", AppCompatActivity.MODE_PRIVATE)

            val edit = sharedEnableN2.edit()

            edit.putInt("EnableN2", enableN2)

            edit.commit()




        }

    }
}

























