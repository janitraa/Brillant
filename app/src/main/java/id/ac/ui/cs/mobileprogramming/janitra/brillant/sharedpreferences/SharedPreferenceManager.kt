package id.ac.ui.cs.mobileprogramming.janitra.brillant.sharedpreferences

import android.content.Context

class SharedPreferenceManager(context: Context) {
    private val FIRST_TIME = "isFirstTime"
    private val key_service = "SERVICE_STATE"
    private val sp = context.getSharedPreferences("profile", Context.MODE_PRIVATE)
    private val sp_editor = sp.edit()

    fun setFirstTime(value: Boolean) {
        sp_editor.putBoolean(FIRST_TIME, value)
        sp_editor.apply()
    }

    fun isFirstTime(): Boolean {
        return sp.getBoolean(FIRST_TIME, true)
    }

    fun setServiceState(stateName: String) {
        sp_editor.putString(key_service, stateName)
        sp_editor.apply()
    }
}