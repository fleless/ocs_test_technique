package tn.ocs.ocs_testtechnique.common

import android.content.Context
import android.content.SharedPreferences
import tn.ocs.ocs_testtechnique.common.constants.PREFS_NAME


class SharedPreference(context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveString(KEY_NAME: String, text: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_NAME, text)
        editor.apply()
    }

    fun getValueString(KEY_NAME: String, defaultValue: String?): String? {
        return sharedPref.getString(KEY_NAME, defaultValue)
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
