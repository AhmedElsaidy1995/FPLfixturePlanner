package com.example.sasadoe.fplfixtureplanner.Controller

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.TextView
import com.example.sasadoe.fplfixtureplanner.Model.Player
import com.example.sasadoe.fplfixtureplanner.R
import com.example.sasadoe.fplfixtureplanner.Services.TeamService.findTeamShortName
import com.example.sasadoe.fplfixtureplanner.Services.UserService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var shirtImages : ArrayList<ImageView>
    lateinit var playerNames : ArrayList<TextView>
    lateinit var playerOpps : ArrayList<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //actionBar.setDisplayShowTitleEnabled(false)
        shirtImages = arrayListOf(player1img,player2img,player3img,player4img,player5img,player6img,player7img,player8img,player9img,player10img,player11img,player12img,player13img,player14img,player15img)
        playerNames = arrayListOf(player1txt,player2txt,player3txt,player4txt,player5txt,player6txt,player7txt,player8txt,player9txt,player10txt,player11txt,player12txt,player13txt,player14txt,player15txt)
        playerOpps  = arrayListOf(player1opp,player2opp,player3opp,player4opp,player5opp,player6opp,player7opp,player8opp,player9opp,player10opp,player11opp,player12opp,player13opp,player14opp,player15opp)

        for (i in 0..14) {
            playerNames[i].text = UserService.myPlayers[i].name
        }

        for (i in 0..14) {
            playerOpps[i].text = UserService.myPlayers[i].opponent
            playerOpps[i].setSelected(true);
        }

        for (i in 0..14) {
            var teamPhoto = findTeamShortName(UserService.myPlayers[i].team)
            if(teamPhoto.equals("NEW")){
                teamPhoto = "NEWC"
            }
            val resourceId = resources.getIdentifier(teamPhoto.toLowerCase(),"drawable",packageName)
            shirtImages[i].setImageResource(resourceId)
        }

        for (i in 0..14) {
            val shirt =  shirtImages[i]
            shirt.setOnClickListener{
                val editPlayerIntent = Intent(this, EditActivity::class.java)
                editPlayerIntent.putExtra("currentPlayer",UserService.myPlayers[i])
                startActivityForResult(editPlayerIntent,100)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100 && resultCode == Activity.RESULT_OK){
            val newPlayer = data!!.getParcelableExtra<Player>("newPlayer")
            UserService.myPlayers[newPlayer.index] = newPlayer
            var teamPhoto = findTeamShortName(UserService.myPlayers[newPlayer.index].team)
            if(teamPhoto.equals("NEW")){
                teamPhoto = "NEWC"
            }
            val resourceId = resources.getIdentifier(teamPhoto.toLowerCase(),"drawable",packageName)
            shirtImages[newPlayer.index].setImageResource(resourceId)
            playerNames[newPlayer.index].setText(newPlayer.name)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.title.equals("Change GW")){
            val builder = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.number_picker_dialog, null)

            val numberPicker = dialogView.findViewById<NumberPicker>(R.id.gwPicker)
            numberPicker.minValue = 34
            numberPicker.maxValue = 38
            builder.setView(dialogView)
                .setPositiveButton("Ok"){ dialogInterface, i ->

                }.setNegativeButton("Cancel"){dialogInterface, i ->

                }.show()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {

    }
}
