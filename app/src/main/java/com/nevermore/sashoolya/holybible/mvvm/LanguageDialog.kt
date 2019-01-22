package com.nevermore.sashoolya.holybible.mvvm

import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.app.LangManager
import com.nevermore.sashoolya.holybible.tools.provider
import kotlinx.android.synthetic.main.dialog_lang.*

class LanguageDialog : AppCompatDialogFragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_lang, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnBel.setOnClickListener {
            provider.langManager.language = LangManager.Language.BEL
            dismiss()
        }

        btnEng.setOnClickListener {
            provider.langManager.language = LangManager.Language.ENG
            dismiss()
        }

        btnRus.setOnClickListener {
            provider.langManager.language = LangManager.Language.RUS
            dismiss()
        }
    }
}