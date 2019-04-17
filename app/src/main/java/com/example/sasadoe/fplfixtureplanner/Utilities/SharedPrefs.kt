package com.example.sasadoe.fplfixtureplanner.Utilities

import android.content.Context
import com.android.volley.toolbox.Volley

class SharedPrefs(context: Context) {
    val PREFS_FILENAME = "prefs"
    val prefs = context.getSharedPreferences(PREFS_FILENAME,0)

    val requestQueue = Volley.newRequestQueue(context)
}