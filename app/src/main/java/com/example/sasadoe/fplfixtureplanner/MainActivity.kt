package com.example.sasadoe.fplfixtureplanner

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import com.example.sasadoe.fplfixtureplanner.R.layout.*

class MainActivity : AppCompatActivity() {

    val players = arrayListOf<Player>()
    val drawablesMap = hashMapOf<String ,Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shirtImages = arrayListOf(player1img,player2img,player3img,player4img,player5img,player6img,player7img,player8img,player9img,player10img,player11img,player12img,player13img,player14img,player15img)
        val playerNames = arrayListOf(player1txt,player2txt,player3txt,player4txt,player5txt,player6txt,player7txt,player8txt,player9txt,player10txt,player11txt,player12txt,player13txt,player14txt,player15txt)
        val playerOpps  = arrayListOf(player1opp,player2opp,player3opp,player4opp,player5opp,player6opp,player7opp,player8opp,player9opp,player10opp,player11opp,player12opp,player13opp,player14opp,player15opp)
        val playerTeams  = arrayListOf("West Ham","Huddersfield","Crystal Palace","Liverpool","Fulham","Wolves","Bournemouth","Everton","Liverpool","Chelsea","Watford","West Ham","Arsenal","Man Utd","Burnley")
        drawablesMap.put("Arsenal", R.drawable.arsenal)
        drawablesMap.put("Bournemouth", R.drawable.bournmouth)
        drawablesMap.put("Brighton", R.drawable.brighton)
        drawablesMap.put("Burnley", R.drawable.burnley)
        drawablesMap.put("Cardiff", R.drawable.cardiff)
        drawablesMap.put("Chelsea", R.drawable.chelsea)
        drawablesMap.put("Crystal Palace", R.drawable.crystalpalace)
        drawablesMap.put("Everton", R.drawable.everton)
        drawablesMap.put("Fulham", R.drawable.fulham)
        drawablesMap.put("Leicester", R.drawable.leicester)
        drawablesMap.put("Huddersfield", R.drawable.huddersfield)
        drawablesMap.put("Liverpool", R.drawable.liverpool)
        drawablesMap.put("Man City", R.drawable.mancity)
        drawablesMap.put("Man Utd", R.drawable.manutd)
        drawablesMap.put("Newcastle", R.drawable.newcastle)
        drawablesMap.put("Southampton", R.drawable.southampton)
        drawablesMap.put("Spurs", R.drawable.spurs)
        drawablesMap.put("Watford", R.drawable.watford)
        drawablesMap.put("West Ham", R.drawable.westham)
        drawablesMap.put("Wolves", R.drawable.wolves)


        for (i in 0..14) {
            players.add(Player(playerNames[i],playerTeams[i],shirtImages[i],playerOpps[i],i))
        }

        for (player in players) {
            val shirt = player.teamShirt
            shirt.setOnClickListener{
                val editPlayerIntent = Intent(this,EditActivity::class.java)
                editPlayerIntent.putExtra("playerName",player.name.text)
                editPlayerIntent.putExtra("playerTeam",player.team)
                editPlayerIntent.putExtra("playerIndex",player.index)
                startActivityForResult(editPlayerIntent,100)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100 && resultCode == Activity.RESULT_OK){
            val newPlayerName = data!!.getStringExtra("newPlayerName")
            val playerIndex = data!!.getIntExtra("newPlayerIndex",15)
            val newPlayerTeam = data!!.getStringExtra("newPlayerTeam")
            val newImage = drawablesMap.get(newPlayerTeam)
            players[playerIndex].team = newPlayerTeam
            players[playerIndex].teamShirt.setImageResource(newImage!!)
            players[playerIndex].name.setText(newPlayerName)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)



        return true
    }
}
