package com.example.sasadoe.fplfixtureplanner.Controller

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.example.sasadoe.fplfixtureplanner.Model.Player
import com.example.sasadoe.fplfixtureplanner.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val players = arrayListOf<Player>()
    val drawablesMap = hashMapOf<String ,Int>()
    lateinit var shirtImages : ArrayList<ImageView>
    lateinit var playerNames : ArrayList<TextView>
    lateinit var playerOpps : ArrayList<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shirtImages = arrayListOf(player1img,player2img,player3img,player4img,player5img,player6img,player7img,player8img,player9img,player10img,player11img,player12img,player13img,player14img,player15img)
        playerNames = arrayListOf(player1txt,player2txt,player3txt,player4txt,player5txt,player6txt,player7txt,player8txt,player9txt,player10txt,player11txt,player12txt,player13txt,player14txt,player15txt)
        playerOpps  = arrayListOf(player1opp,player2opp,player3opp,player4opp,player5opp,player6opp,player7opp,player8opp,player9opp,player10opp,player11opp,player12opp,player13opp,player14opp,player15opp)
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
            players.add(
                Player(
                    playerNames[i].text as String,
                    playerTeams[i],
                    playerOpps[i].text as String,
                    i
                )
            )
        }

        for (i in 0..14) {
            val shirt = shirtImages[i]
            shirt.setOnClickListener{
                val editPlayerIntent = Intent(this, EditActivity::class.java)
                editPlayerIntent.putExtra("currentPlayer",players[i])
                startActivityForResult(editPlayerIntent,100)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100 && resultCode == Activity.RESULT_OK){
            val newPlayer = data!!.getParcelableExtra<Player>("newPlayer")
            val newImage = drawablesMap.get(newPlayer.team)
            players[newPlayer.index] = newPlayer
            shirtImages[newPlayer.index].setImageResource(newImage!!)
            playerNames[newPlayer.index].setText(newPlayer.name)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)



        return true
    }
}
