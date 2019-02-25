package com.example.sasadoe.fplfixtureplanner.Controller

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sasadoe.fplfixtureplanner.Model.Player
import com.example.sasadoe.fplfixtureplanner.R
import kotlinx.android.synthetic.main.edit_activity.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_activity)

        val res = getResources()
        val intent = getIntent()

        val currentPlayer = intent.getParcelableExtra<Player>("currentPlayer")

        val labels = res.getStringArray(R.array.Teams_array)
        val teamIndex  = labels.indexOf(currentPlayer.team)
        spinner.setSelection(teamIndex)
        editText.setText(currentPlayer.name)

        confirmbutton.setOnClickListener{
            val newName = "" + editText.text
            val newTeam = spinner.selectedItem.toString()
            if (newTeam.equals("Select Team") || newName.equals("")){
               Toast.makeText(this,"Please Complete Player info",Toast.LENGTH_SHORT).show()
            }
            else {
                val mainActivityIntent = Intent()
                currentPlayer.name = newName
                currentPlayer.team = newTeam
                mainActivityIntent.putExtra("newPlayer", currentPlayer)

                setResult(Activity.RESULT_OK, mainActivityIntent)
                finish()
            }
        }

    }


}
