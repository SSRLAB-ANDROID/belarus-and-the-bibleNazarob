package com.nevermore.sashoolya.holybible.vm

import androidx.lifecycle.ViewModel
import com.nevermore.sashoolya.holybible.data.pojo.Exposition

class ExponateViewModel : ViewModel() {

    private var exponate: Exposition? = null

    fun getExponate() = exponate

    fun setExponate(exponate: Exposition){
        this.exponate = exponate
    }
}