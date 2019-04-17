package com.example.sasadoe.fplfixtureplanner.Services

import android.util.Log
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.JsonArrayRequest
import com.example.sasadoe.fplfixtureplanner.Controller.App
import com.example.sasadoe.fplfixtureplanner.Model.Team
import com.example.sasadoe.fplfixtureplanner.Utilities.URL_GAMEWEEKS
import com.example.sasadoe.fplfixtureplanner.Utilities.URL_TEAMS
import org.json.JSONException

object TeamService {
    var teams = arrayListOf<Team>()

    fun loadTeams(complete:(Boolean)-> Unit){
        val teamsRequest = object: JsonArrayRequest(Method.GET, URL_TEAMS,null, Listener { response ->
            try {
                for (x in 0..19) {
                    val team_id = response.getJSONObject(x).getInt("id")
                    val team_name = response.getJSONObject(x).getString("name")
                    val team_shortName = response.getJSONObject(x).getString("short_name")
                    val team_code = response.getJSONObject(x).getInt("code")

                    val next_opp = response.getJSONObject(x).getJSONArray("next_event_fixture")
                    var c = 0
                    var opp = ""
                    while(c < next_opp.length()){
                        if(c != 0){
                            opp += ", "
                        }
                        for(i in 0..19){
                            if(next_opp.getJSONObject(c).getInt("opponent").equals(response.getJSONObject(i).getInt("id"))){
                                opp += response.getJSONObject(i).getString("short_name")
                            }
                        }
                        if(next_opp.getJSONObject(c).getBoolean("is_home")){
                            opp += " (H)"
                        }else{
                            opp += " (A)"
                        }
                        c++
                    }
                    println((opp) + " ////////////////////////////////////////////////")
                    teams.add(Team(team_name,team_shortName,opp,team_id,team_code))
                }
                complete(true)
            } catch (e: JSONException) {
                complete(false)
            }
        }, Response.ErrorListener { error ->
                Log.d("ERROR","Couldn't FIND TEAMS: $error")
                complete(false)
            }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
        }
        App.prefs.requestQueue.add(teamsRequest)

    }

    fun findTeamByCode(code:Int) : String{
        for (x in 0..19){
            if(code == teams[x].code){
                return teams[x].name
            }
        }
        return "null"
    }

    fun findTeamNextOpp(code:Int) : String{
        for (x in 0..19){
            if(code == teams[x].code){
                return teams[x].nextOpp
            }
        }
        return "null"
    }

    fun findTeamShortName(name:String):String{
        for (x in 0..19){
            if(name == teams[x].name){
                return teams[x].shortName
            }
        }
        return "null"
    }

}