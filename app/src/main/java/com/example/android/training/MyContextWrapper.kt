package com.example.android.training

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import java.util.*

class MyContextWrapper(context: Context) : ContextWrapper(context) {
    companion object {

        @Suppress("DEPRECATION")
        fun wrap(ctx: Context, language: String): ContextWrapper {
            var context = ctx
            val config = context.resources.configuration
            val sysLocale: Locale?
            sysLocale =
                getSystemLocale(config)
            if (language != "" && sysLocale.language != language) {
                val locale = Locale(language)
                Locale.setDefault(locale)
                setSystemLocale(config, locale)

            }
            context = context.createConfigurationContext(config)
            return MyContextWrapper(context)
        }

        @Suppress("DEPRECATION")
        private fun getSystemLocaleLegacy(config: Configuration): Locale {
            return config.locale
        }

        fun getSystemLocale(config: Configuration): Locale {
            return config.locales.get(0)
        }

        @Suppress("DEPRECATION")
        private fun setSystemLocaleLegacy(config: Configuration, locale: Locale) {
            config.locale = locale
        }

        fun setSystemLocale(config: Configuration, locale: Locale) {
            config.setLocale(locale)
        }
    }
}