package com.example.sasadoe.fplfixtureplanner.Services
import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.sasadoe.fplfixtureplanner.Controller.App
import com.example.sasadoe.fplfixtureplanner.Model.Player
import com.example.sasadoe.fplfixtureplanner.Utilities.URL
import com.example.sasadoe.fplfixtureplanner.Utilities.URL_GAMEWEEKS
import com.example.sasadoe.fplfixtureplanner.Utilities.URL_PLAYERS
import org.json.JSONException


object UserService{
    var myPlayers = arrayListOf<Player>()
    var playerIDs = arrayListOf<Int>()
    var currentGW = 0   ;
    var userID = 100996 ;

    fun findMyPlayers(complete:(Boolean)-> Unit){
        val url = URL + "entry/" + userID + "/event/" + currentGW + "/picks"
        val playersRequest = object: JsonObjectRequest(Method.GET, url,null, Listener { response ->
            try {
                val players = response.getJSONArray("picks")
                for (x in 0..14){
                    val playerId = players.getJSONObject(x).getInt("element")
                    playerIDs.add(playerId)
                }
                complete(true)
            }catch (e: JSONException){
                complete(false)
            }
        },Response.ErrorListener { error ->
            Log.d("ERROR","Couldn't FIND PLAYERS: $error")
            complete(false)
        }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
        }
        App.prefs.requestQueue.add(playersRequest)
    }

    fun findPlayersById(complete:(Boolean)-> Unit){
        val playerRequest = object: JsonArrayRequest(Method.GET, URL_PLAYERS,null, Listener { response ->
            try {
                println("searching for id "+myPlayers.size)
                for (x in 0..14) {
                    var c = 0
                    while (c < response.length()) {
                        if (response.getJSONObject(c).getInt("id") == playerIDs[x]) {
                            val playerName = response.getJSONObject(c).getString("web_name")
                            val teamCode = response.getJSONObject(c).getInt("team_code")
                            val playerPos = response.getJSONObject(c).getInt("element_type")
                            val playerIndex = myPlayers.size
                            val teamName = TeamService.findTeamByCode(teamCode)
                            val nextOpp = TeamService.findTeamNextOpp(teamCode)
                            myPlayers.add(Player(playerIDs[x], playerName, teamName, nextOpp, playerPos,playerIndex))
                            break
                        } else {
                            c++
                        }
                    }
                }
                myPlayers.sortBy { selector(it) }
                for (x in 0..14) {
                    myPlayers[x].index = x
                }
                complete(true)
            }catch (e: JSONException){
                complete(false)
            }
        },Response.ErrorListener { error ->
            Log.d("ERROR","Couldn't FIND PLAYER BY ID: $error")
            complete(false)

        }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
        }
        App.prefs.requestQueue.add(playerRequest)
    }

    fun selector(p: Player): Int = p.position


    fun getCurrentGW(complete:(Boolean)-> Unit){
        val gwRequest = object: JsonArrayRequest(Method.GET, URL_GAMEWEEKS,null, Listener { response ->
            try {
                for (x in 0..37){
                    if(response.getJSONObject(x).getBoolean("is_next"))
                        currentGW = x
                }
                complete(true)
            }catch (e: JSONException){
                complete(false)
            }
        },Response.ErrorListener { error ->
            Log.d("ERROR","Couldn't FIND GAMEWEEKS: $error")
            complete(false)
        }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
        }
        App.prefs.requestQueue.add(gwRequest)

    }



}