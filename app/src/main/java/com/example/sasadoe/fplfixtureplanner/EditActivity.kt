package com.example.sasadoe.fplfixtureplanner

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import kotlinx.android.synthetic.main.edit_activity.*

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_activity)
        val res = getResources()

        val intent = getIntent()
        val playerName = intent.getStringExtra("playerName")
        val playerTeam = intent.getStringExtra("playerTeam")
        val labels = res.getStringArray( R.array.Teams_array )
        val index  = labels.indexOf(playerTeam)
        spinner.setSelection(index)
        editText.setText(playerName)

    }


}
