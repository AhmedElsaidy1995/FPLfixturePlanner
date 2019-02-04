package com.example.sasadoe.fplfixtureplanner

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shirtImages = arrayListOf(player1img,player2img,player3img,player4img,player5img,player6img,player7img,player8img,player9img,player10img,player11img,player12img,player13img,player14img,player15img)

        for (shirt in shirtImages) {
            shirt.setOnClickListener{
                val editPlayerIntent = Intent(this,EditActivity::class.java)
                startActivity(editPlayerIntent)
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)



        return true
    }
}
