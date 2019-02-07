package com.example.sasadoe.fplfixtureplanner

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.edit_activity.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_activity)

        val res = getResources()
        val intent = getIntent()
        val playerName = intent.getStringExtra("playerName")
        val playerTeam = intent.getStringExtra("playerTeam")
        val playerIndex = intent.getIntExtra("playerIndex",0)
        val labels = res.getStringArray( R.array.Teams_array )
        val teamIndex  = labels.indexOf(playerTeam)
        spinner.setSelection(teamIndex)
        editText.setText(playerName)

        confirmbutton.setOnClickListener{
            val newName = "" + editText.text
            val newTeam = spinner.selectedItem.toString()
            if (newTeam.equals("Select Team") || newName.equals("")){
               Toast.makeText(this,"Please Complete Player info",Toast.LENGTH_SHORT).show()
            }
            else {
                val mainActivityIntent = Intent()
                mainActivityIntent.putExtra("newPlayerIndex", playerIndex)
                mainActivityIntent.putExtra("newPlayerTeam", newTeam)
                mainActivityIntent.putExtra("newPlayerName", newName)
                setResult(Activity.RESULT_OK, mainActivityIntent)
                finish()
            }
        }

    }


}
