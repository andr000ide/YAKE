package com.example.yake.Auxiliares

import android.content.Context
import android.content.res.Configuration
import com.example.yake.R
import java.util.*

class LangHelper(context: Context) {

    companion object {
        const val LANG_KEY = "favorites"
    }

    private val mSharedPreferences =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)



    fun setNewLocale(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val res = context.resources
        val config = Configuration(res.configuration)
        config.locale = locale
        res.updateConfiguration(config, res.displayMetrics)
        saveLanguage(language)
        return context
    }



    fun saveLanguage(lang: String) { mSharedPreferences.edit().putString(LANG_KEY, lang).apply() }

    fun getLanguageSaved() = mSharedPreferences.getString(LANG_KEY, "pt")

}