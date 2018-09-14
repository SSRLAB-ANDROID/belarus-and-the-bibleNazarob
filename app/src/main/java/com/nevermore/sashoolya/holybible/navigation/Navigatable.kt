package com.nevermore.sashoolya.holybible.navigation

interface Navigatable {
    fun navigateTo(screen : String)
    fun replaceBy(screen : String)
    fun backTo(screen : String)
    fun exit()
    fun newRootScreen(scren : String)
}