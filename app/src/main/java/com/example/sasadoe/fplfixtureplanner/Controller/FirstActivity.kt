package com.example.sasadoe.fplfixtureplanner.Controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.sasadoe.fplfixtureplanner.Services.UserService
import com.example.sasadoe.fplfixtureplanner.R
import com.example.sasadoe.fplfixtureplanner.R.id.*
import com.example.sasadoe.fplfixtureplanner.Services.TeamService
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
    }

    fun loadTeam(view: View){
        enableSpinner(true)
        UserService.userID = idInputTxt.text.toString().toInt()
        UserService.getCurrentGW{ gwSucess ->
            if(gwSucess){
                TeamService.loadTeams { teamsSucces ->
                    if(teamsSucces){
                        UserService.findMyPlayers { playersSuccess  ->
                            if(playersSuccess){
                                runOnUiThread {
                                    UserService.findPlayersById { complete ->
                                        if(complete){
                                            val mainActivityIntent = Intent(this, MainActivity::class.java)
                                            startActivity(mainActivityIntent)
                                            enableSpinner(false)
                                        }
                                    }
                                }
                                    /*for (x in 0..UserService.myPlayers.size){
                                        println(UserService.myPlayers[x].name + "1111111111111111111111111111111111111111111111111111111111111")
                                    }*/
                            }
                        }
                    }
                }
            }
        }
    }

    fun enableSpinner(enable: Boolean){
        if(enable){
            dataSpinner.visibility = View.VISIBLE
            idInputTxt.isEnabled = false
            loadTeamBtn.isEnabled = false
        }else{
            dataSpinner.visibility = View.INVISIBLE
            idInputTxt.isEnabled = true
            loadTeamBtn.isEnabled = true
        }
    }
}
