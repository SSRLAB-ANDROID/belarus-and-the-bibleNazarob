package com.nevermore.sashoolya.holybible.app

import android.content.Context
import android.os.Build

import android.os.LocaleList
import java.util.*


class ContextWrapper(base: Context) : android.content.ContextWrapper(base) {
    companion object {

        fun wrap(context: Context, newLocale: Locale): ContextWrapper {
            var context = context

            val res = context.getResources()
            val configuration = res.getConfiguration()

            if (Build.VERSION.SDK_INT >= 24) {
                configuration.setLocale(newLocale)

                val localeList = LocaleList(newLocale)
                LocaleList.setDefault(localeList)
                configuration.setLocales(localeList)

                context = context.createConfigurationContext(configuration)

            } else if (Build.VERSION.SDK_INT >= 17) {
                configuration.setLocale(newLocale)
                context = context.createConfigurationContext(configuration)

            } else {
                configuration.locale = newLocale
                res.updateConfiguration(configuration, res.getDisplayMetrics())
            }

            return ContextWrapper(context)
        }
    }
}