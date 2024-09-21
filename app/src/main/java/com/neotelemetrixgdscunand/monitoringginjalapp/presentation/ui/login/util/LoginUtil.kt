package com.neotelemetrixgdscunand.monitoringginjalapp.presentation.ui.login.util

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.neotelemetrixgdscunand.monitoringginjalapp.R

object LoginUtil {
    fun getLanguageLocaleOptions(context: Context): Map<String, String> {
        val indonesiaLanguageCode = "in"
        val englishLanguageCode = "eng"
        val minangLanguageCode = "min"

        return mapOf(
            context.getString(R.string.indonesia) to indonesiaLanguageCode,
            context.getString(R.string.english) to englishLanguageCode,
            context.getString(R.string.minang) to minangLanguageCode
        )
    }

    fun setNewLanguageLocale(selectedLanguageCode: String){
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(
                selectedLanguageCode
            )
        )
    }
}