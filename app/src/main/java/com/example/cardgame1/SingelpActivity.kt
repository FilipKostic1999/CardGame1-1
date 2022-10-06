package com.example.cardgame1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class SingelpActivity : AppCompatActivity() {

    lateinit var cover_1: ImageView
    lateinit var cover_2: ImageView

    lateinit var points_Pl1: TextView
    lateinit var points_Pl2: TextView
    lateinit var textView : TextView

    lateinit var draw_1: TextView

    lateinit var random: Random

    var riskPl1 : Boolean = false
    var riskPl2 : Boolean = false
    var bonus : Int = 5

    lateinit var boll_1: ImageView

    lateinit var riskPl1B : Button

    var player1 = 0
    var player2 = 0

    var cardsArray = intArrayOf(

        R.drawable.c2bc,
        R.drawable.c3bc,
        R.drawable.c4bc,
        R.drawable.c5bc,
        R.drawable.c6bc,
        R.drawable.c7rd,
        R.drawable.c8bc,
        R.drawable.c9bc,
        R.drawable.c10bc,
        R.drawable.cjbc,
        R.drawable.cqbc,
        R.drawable.ckrd,
        R.drawable.cabc,

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singelp)

            random = Random

        val sharedEnableN2 = getSharedPreferences("EnableN2", AppCompatActivity.MODE_PRIVATE)
        val EnableN2 = sharedEnableN2.getInt("EnableN2", 0)

            cover_1 = findViewById(R.id.cover_1)
            cover_2 = findViewById(R.id.cover_2)

             textView = findViewById(R.id.textView)

            boll_1 = findViewById(R.id.imageView)

            cover_1.setImageResource(R.drawable.bc)
            cover_2.setImageResource(R.drawable.bc)

        riskPl1B = findViewById(R.id.riskPlayer1)

            points_Pl1 = findViewById(R.id.points_Pl1)
            points_Pl2 = findViewById(R.id.points_Pl2)

            draw_1 = findViewById(R.id.draw_1)
            draw_1.visibility = View.INVISIBLE


        cover_1 = findViewById(R.id.cover_1)




        riskPl1B.setOnClickListener {

if (EnableN2 == 0) {
    riskPl1 = true
    boll_1.setImageResource(R.drawable.skull)
}

        }




            cover_1.setOnClickListener{
                draw_1.visibility = View.INVISIBLE

                val card1 = random.nextInt(cardsArray.size)
                val card2 = random.nextInt(cardsArray.size)

                setCardImage(card1, cover_1)
                setCardImage(card2, cover_2)


var randomN = (1..10).random()


                textView.text = ""

if (EnableN2 == 0) {

                if (randomN > 3) {
                    riskPl2 = false
                } else if (randomN <= 3) {
                    riskPl2 = true
                }
}



                if(card1 > card2) {

                    if(riskPl1 == true) {
                        player1 = player1 + bonus
                        points_Pl1.text = "points: $player1"
                    } else if (riskPl1 == false) {
                        player1++
                        points_Pl1.text = "points: $player1"
                    }


                    if(riskPl2 == true && riskPl1 == false) {
                        player1 = player1 + (bonus - 1 )
                        points_Pl1.text = "points: $player1"
                    } else if (riskPl2 == true && riskPl1 == true) {
                        player1 = player1 + bonus
                        points_Pl1.text = "points: $player1"
                    }


                } else if (card1 < card2) {

                    if(riskPl2 == true) {
                        player2 = player2 + bonus
                        points_Pl2.text = "points: $player2"
                    } else if (riskPl2 == false) {
                        player2++
                        points_Pl2.text = "points: $player2"

                    }

                    if(riskPl1 == true && riskPl2 == false) {
                        player2 = player2 + (bonus - 1 )
                        points_Pl2.text = "points: $player2"
                    }  else if (riskPl1 == true && riskPl2 == true) {
                        player2 = player2 + bonus
                        points_Pl2.text = "points: $player2"
                    }


                } else {
                    draw_1.visibility = View.VISIBLE
            }

                if (EnableN2 == 0) {

                if (randomN <= 3) {
                    riskPl2 = false
                    textView.text = "Comp used risky move"
                }
                }

                riskPl1 = false
                boll_1.setImageResource(R.drawable.boll)


                if (player1 >= 20) {



                    val intent = Intent(this, WinActivity::class.java)
                    startActivity(intent)
                } else if (player2 >= 20) {




                    val intent = Intent(this, WinActivity3::class.java)
                    startActivity(intent)
                }
        }
    }
        fun setCardImage(number: Int, image: ImageView){
            image.setImageResource(cardsArray[number])
        }

    }



