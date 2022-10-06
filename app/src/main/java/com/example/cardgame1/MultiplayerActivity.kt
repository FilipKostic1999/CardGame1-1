package com.example.cardgame1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class MultiplayerActivity : AppCompatActivity() {


    lateinit var cover1: ImageView
    lateinit var cover2: ImageView
    lateinit var riskP1: ImageView
    lateinit var riskP2: ImageView

    lateinit var ketrenBomb1: ImageView
    lateinit var ketrenBomb2: ImageView

    lateinit var points_pl1: TextView
    lateinit var points_pl2: TextView

    lateinit var draw: TextView

    lateinit var riskPlayer1 : Button
    lateinit var riskPlayer2 : Button

    lateinit var random: Random

    var bonusActivatorP1 : Boolean = false
    var bonusActivatorP2 : Boolean = false
    var bonus : Int = 5
    var ketren : Int = 0
    var ketren2 : Int = 0


    var pointsPl1 = 0
    var pointsPl2 = 0

    var randIndex2 = -1
    var randIndex = -1
    var ketrenBombFactor = 20

    val deckOfCard = mutableListOf<Card>()
    var player1Card: Card? = null
    var player2Card: Card? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplayer)






        random = Random()

        ketrenBomb1 = findViewById(R.id.ketrenBomb1)
        ketrenBomb2 = findViewById(R.id.ketrenBomb2)

        cover1 = findViewById(R.id.cover1)
        cover2 = findViewById(R.id.cover2)
        riskP1 = findViewById(R.id.warning1)
        riskP2 = findViewById(R.id.warning2)

        cover1.setImageResource(R.drawable.bc)
        cover2.setImageResource(R.drawable.bc)

        points_pl1 = findViewById(R.id.points_Pl1)
        points_pl2 = findViewById(R.id.points_Pl2)


        riskPlayer1 = findViewById(R.id.riskPlayer1)
        riskPlayer2 = findViewById(R.id.riskPlayer2)

        draw = findViewById(R.id.draw1)
        draw.visibility = View.INVISIBLE

        createCards()


        val shared = getSharedPreferences("Score", MODE_PRIVATE)
        ketren = shared.getInt("Score", 0)
        ketren2 = shared.getInt("Score2", 0)



        val sharedEnableN = getSharedPreferences("EnableN", AppCompatActivity.MODE_PRIVATE)
        val EnableN = sharedEnableN.getInt("EnableN", 0)


        val sharedEnableN2 = getSharedPreferences("EnableN2", AppCompatActivity.MODE_PRIVATE)
        val EnableN2 = sharedEnableN2.getInt("EnableN2", 0)


        ketrenBomb1.setOnClickListener{

            if (EnableN == 0) {



            ketrenBomb1.setImageResource(R.drawable.ketrenmushroom)
            draw.visibility = View.INVISIBLE
            randIndex = random.nextInt(deckOfCard.size)
            player1Card = deckOfCard[randIndex]
            cover1.setImageResource(player1Card!!.image)

            var random = (1..100).random()

            if (random <= 15) {
                pointsPl1 = pointsPl1 + ketrenBombFactor
            } else if (random > 15) {
                pointsPl2 = pointsPl2 + ketrenBombFactor
            }

            if (pointsPl1 >= 20) {

                ketren ++
                val shared = getSharedPreferences("Score", MODE_PRIVATE)

                val edit = shared.edit()

                edit.putInt("Score", ketren)

                edit.commit()

                val intent = Intent(this, WinActivity::class.java)
                startActivity(intent)
            } else if (pointsPl2 >= 20) {

                ketren2 ++
                val shared = getSharedPreferences("Score", MODE_PRIVATE)

                val edit = shared.edit()

                edit.putInt("Score2", ketren2)

                edit.commit()

                val intent = Intent(this, activity_win2::class.java)
                startActivity(intent)
            }

            }
        }


            ketrenBomb2.setOnClickListener{




                if (EnableN == 0) {






            ketrenBomb2.setImageResource(R.drawable.ketrenmushroom)


            draw.visibility = View.INVISIBLE
            randIndex = random.nextInt(deckOfCard.size)
            player1Card = deckOfCard[randIndex]
            cover1.setImageResource(player1Card!!.image)

            var random2 = (1..100).random()

            if (random2 <= 15) {
                pointsPl2 = pointsPl2 + ketrenBombFactor
            } else if (random2 > 15) {
                pointsPl1 = pointsPl1 + ketrenBombFactor
            }

            if (pointsPl1 >= 20) {

                ketren ++
                val shared = getSharedPreferences("Score", MODE_PRIVATE)

                val edit = shared.edit()

                edit.putInt("Score", ketren)

                edit.commit()

                val intent = Intent(this, WinActivity::class.java)
                startActivity(intent)
            } else if (pointsPl2 >= 20) {

                ketren2 ++
                val shared = getSharedPreferences("Score2", MODE_PRIVATE)

                val edit = shared.edit()

                edit.putInt("Score2", ketren2)

                edit.commit()

                val intent = Intent(this, activity_win2::class.java)
                startActivity(intent)
            }

                }

        }


        cover1.setOnClickListener {

            cover1?.isEnabled = false
            cover2?.isEnabled = true
            riskPlayer1?.isEnabled = false
            riskPlayer2?.isEnabled = true
            ketrenBomb1?.isEnabled = false
            ketrenBomb2?.isEnabled = true

            draw.visibility = View.INVISIBLE
            randIndex = random.nextInt(deckOfCard.size)
            player1Card = deckOfCard[randIndex]
            cover1.setImageResource(player1Card!!.image)

            Log.d("!!!", "1: ${player1Card?.value} 2: ${player2Card?.value}")

            if (player1Card != null && player2Card != null) {
                if (player1Card!!.value > player2Card!!.value) {

                   if (bonusActivatorP1) {
                       pointsPl1 += bonus
                       bonusActivatorP1 = false
                       points_pl1.text = "pointsPl1: $pointsPl1"
                       riskP1.setImageResource(R.drawable.boll)
                   } else if (!bonusActivatorP1) {
                       pointsPl1++
                       points_pl1.text = "pointsPl1: $pointsPl1"
                   }

                } else if (player1Card!!.value < player2Card!!.value) {

                    if (bonusActivatorP1) {
                        pointsPl2 += bonus
                        bonusActivatorP1 = false
                        points_pl2.text = "pointsPl2: $pointsPl2"
                        riskP1.setImageResource(R.drawable.boll)

                    } else if (!bonusActivatorP1) {
                        pointsPl2++
                        points_pl2.text = "pointsPl2: $pointsPl2"
                    }

                }  else if (player1Card!!.value == player2Card!!.value) {
                    draw.visibility = View.VISIBLE


                    riskP1.setImageResource(R.drawable.boll)
                    bonusActivatorP1 = false
                }

            } else {
                bonusActivatorP1 = false
                bonusActivatorP2 = false
            }














            if (pointsPl1 >= 20) {

                ketren ++
                val shared = getSharedPreferences("Score", MODE_PRIVATE)

                val edit = shared.edit()

                edit.putInt("Score", ketren)

                edit.commit()

                val intent = Intent(this, WinActivity::class.java)
                startActivity(intent)
            } else if (pointsPl2 >= 20) {

                ketren2 ++
                val shared = getSharedPreferences("Score2", MODE_PRIVATE)

                val edit = shared.edit()

                edit.putInt("Score2", ketren2)

                edit.commit()

                val intent = Intent(this, activity_win2::class.java)
                startActivity(intent)
            }
        }

        riskPlayer1.setOnClickListener {

            if (EnableN2 == 0) {
                if (player2Card != null) {
                    riskP1.setImageResource(R.drawable.skull)
                }
                bonusActivatorP1 = true
            }
        }

        riskPlayer2.setOnClickListener {

            if (EnableN2 == 0) {
                if (player1Card != null) {
                    riskP2.setImageResource(R.drawable.skull)
                }
            bonusActivatorP2 = true
            }
        }

        cover2.setOnClickListener {

            cover1?.isEnabled = true
            cover2?.isEnabled = false
            riskPlayer1?.isEnabled = true
            riskPlayer2?.isEnabled = false
            ketrenBomb1?.isEnabled = true
            ketrenBomb2?.isEnabled = false

            draw.visibility = View.INVISIBLE

            randIndex2 = random.nextInt(deckOfCard.size)
            player2Card = deckOfCard[randIndex2]
            cover2.setImageResource(player2Card!!.image)

            if (player2Card != null && player1Card != null) {
                if (player1Card!!.value > player2Card!!.value) {

                    if (bonusActivatorP2) {
                        pointsPl1 += bonus
                        bonusActivatorP2 = false
                        points_pl1.text = "pointsPl1: $pointsPl1"
                        riskP2.setImageResource(R.drawable.boll)
                    } else if (!bonusActivatorP2) {
                        pointsPl1++
                        points_pl1.text = "pointsPl1: $pointsPl1"
                    }

                } else if (player1Card!!.value < player2Card!!.value) {

                    if (bonusActivatorP2) {
                        pointsPl2 += bonus
                        bonusActivatorP2 = false
                        points_pl2.text = "pointsPl2: $pointsPl2"
                        riskP2.setImageResource(R.drawable.boll)

                    } else if (!bonusActivatorP2) {
                        pointsPl2++
                        points_pl2.text = "pointsPl2: $pointsPl2"
                    }

                } else if (player1Card!!.value == player2Card!!.value) {
                    draw.visibility = View.VISIBLE
                    bonusActivatorP2 = false
                    riskP2.setImageResource(R.drawable.boll)
                }


            } else {
                bonusActivatorP1 = false
                bonusActivatorP2 = false
            }

            if (pointsPl1 >= 20) {

                ketren ++
                val shared = getSharedPreferences("Score", MODE_PRIVATE)

                val edit = shared.edit()

                edit.putInt("Score", ketren)

                edit.commit()

                val intent = Intent(this, WinActivity::class.java)
                startActivity(intent)
            } else if (pointsPl2 >= 20) {

                ketren2 ++
                val shared = getSharedPreferences("Score2", MODE_PRIVATE)

                val edit = shared.edit()

                edit.putInt("Score2", ketren2)

                edit.commit()


                val intent = Intent(this, activity_win2::class.java)
                startActivity(intent)
            }
        }
    }
    fun createCards() {

        deckOfCard.add(Card(R.drawable.c2bc, 2))
        deckOfCard.add(Card(R.drawable.c3bc, 3))
        deckOfCard.add(Card(R.drawable.c4bc, 4))
        deckOfCard.add(Card(R.drawable.c5bc, 5))
        deckOfCard.add(Card(R.drawable.c6bc, 6))
        deckOfCard.add(Card(R.drawable.c7bc, 7))
        deckOfCard.add(Card(R.drawable.c8bc, 8))
        deckOfCard.add(Card(R.drawable.c9bc, 9))
        deckOfCard.add(Card(R.drawable.c10bc, 10))
        deckOfCard.add(Card(R.drawable.cjbc, 11))
        deckOfCard.add(Card(R.drawable.cqbc, 12))
        deckOfCard.add(Card(R.drawable.ckbc, 13))
        deckOfCard.add(Card(R.drawable.cabc, 14))


        deckOfCard.add(Card(R.drawable.c2rd, 2))
        deckOfCard.add(Card(R.drawable.c3rd, 3))
        deckOfCard.add(Card(R.drawable.c4rd, 4))
        deckOfCard.add(Card(R.drawable.c5rd, 5))
        deckOfCard.add(Card(R.drawable.c6rd, 6))
        deckOfCard.add(Card(R.drawable.c7rd, 7))
        deckOfCard.add(Card(R.drawable.c8rd, 8))
        deckOfCard.add(Card(R.drawable.c9rd, 9))
        deckOfCard.add(Card(R.drawable.c10rd, 10))
        deckOfCard.add(Card(R.drawable.cjrd, 11))
        deckOfCard.add(Card(R.drawable.cqrd, 12))
        deckOfCard.add(Card(R.drawable.ckrd, 13))
        deckOfCard.add(Card(R.drawable.card, 14))


        deckOfCard.add(Card(R.drawable.c2bs, 2))
        deckOfCard.add(Card(R.drawable.c3bs, 3))
        deckOfCard.add(Card(R.drawable.c4bs, 4))
        deckOfCard.add(Card(R.drawable.c5bs, 5))
        deckOfCard.add(Card(R.drawable.c6bs, 6))
        deckOfCard.add(Card(R.drawable.c7bs, 7))
        deckOfCard.add(Card(R.drawable.c8bs, 8))
        deckOfCard.add(Card(R.drawable.c9bs, 9))
        deckOfCard.add(Card(R.drawable.c10bs, 10))
        deckOfCard.add(Card(R.drawable.cjbs, 11))
        deckOfCard.add(Card(R.drawable.cqbs, 12))
        deckOfCard.add(Card(R.drawable.ckbs, 13))
        deckOfCard.add(Card(R.drawable.cabs, 14))


        deckOfCard.add(Card(R.drawable.c2rh, 2))
        deckOfCard.add(Card(R.drawable.c3rh, 3))
        deckOfCard.add(Card(R.drawable.c4rh, 4))
        deckOfCard.add(Card(R.drawable.c5rh, 5))
        deckOfCard.add(Card(R.drawable.c6rh, 6))
        deckOfCard.add(Card(R.drawable.c7rh, 7))
        deckOfCard.add(Card(R.drawable.c8rh, 8))
        deckOfCard.add(Card(R.drawable.c9rh, 9))
        deckOfCard.add(Card(R.drawable.c10rh, 10))
        deckOfCard.add(Card(R.drawable.cjrh, 11))
        deckOfCard.add(Card(R.drawable.cqrh, 12))
        deckOfCard.add(Card(R.drawable.ckrh, 13))
        deckOfCard.add(Card(R.drawable.carh, 14))
    }
}

