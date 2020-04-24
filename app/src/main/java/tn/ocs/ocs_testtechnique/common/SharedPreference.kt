package tn.ocs.ocs_testtechnique.common

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import tn.ocs.ocs_testtechnique.common.application.App
import tn.ocs.ocs_testtechnique.common.constants.PREFS_NAME
import tn.ocs.ocs_testtechnique.data.model.Content


class SharedPreference(context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveString(KEY_NAME: String, text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_NAME, text)
        editor.apply()
    }

    fun saveContent(KEY_NAME: String, movie: Content) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        val gson = Gson()
        val json = gson.toJson(movie)
        editor.putString(KEY_NAME, json)
        editor.apply()
    }

    fun getValueString(KEY_NAME: String, defaultValue: String?): String? {
        return sharedPref.getString(KEY_NAME, defaultValue)
    }

    fun getValueContent(KEY_NAME: String): Content? {
        val gson = Gson()
        val json: String = sharedPref.getString(KEY_NAME, null)!!
        return gson.fromJson(json, Content::class.java)
    }

    fun clearSharedPreference() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }

    fun removeValue(KEY_NAME: String) {

        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.remove(KEY_NAME)
        editor.apply()
    }
}
