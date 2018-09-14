package com.nevermore.sashoolya.holybible.navigation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.util.provider
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace

class RootActivity : AppCompatActivity(), Navigatable {

    private val navigator = RootNavigator(this)

    val router = provider.rootRouter

    val navigatorHolder = provider.rootNavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(FrameLayout(this).apply { id = R.id.container })
        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(RootScreens.SECTIONS_SCREEN, null)))
        }
    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment is Navigatable){
            fragment.exit()
        }else{
            super.onBackPressed()
        }
    }

    override fun navigateTo(screen: String){
        router.navigateTo(screen)
    }
    override fun replaceBy(screen: String){
        router.replaceScreen(screen)
    }

    override fun backTo(screen: String){
        router.backTo(screen)
    }

    override fun exit(){
        router.exit()
    }

    override fun newRootScreen(scren: String) {
        router.newRootScreen(scren)
    }
}
