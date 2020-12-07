package id.itborneo.blanjaa.core.utils.user

import android.content.Context
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import id.itborneo.blanjaa.core.data.model.UserModel
import id.itborneo.blanjaa.core.utils.constant.PREF_User

object Login {

    fun setLastLogin(context: Context, userModel: UserModel) {

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPref.edit()
        val gson = Gson()
        val json = gson.toJson(userModel)
        editor.putString(PREF_User, json)
        editor.apply()
    }

    fun getLastUser(context: Context): UserModel? {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)

        val gson = Gson()
        val json: String? = sharedPref.getString(PREF_User, "")
        return if (json == null || json == "") {
            null
        } else {
            gson.fromJson(json, UserModel::class.java)

        }
    }

    fun removeLastUser(context: Context) {
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }
}
