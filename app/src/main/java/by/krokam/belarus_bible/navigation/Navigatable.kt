package by.krokam.belarus_bible.navigation

interface Navigatable {
    fun navigateTo(screen : String)
    fun replaceBy(screen : String)
    fun backTo(screen : String)
    fun exit()
    fun newRootScreen(screen : String)
}