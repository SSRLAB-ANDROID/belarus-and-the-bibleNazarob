package com.nevermore.sashoolya.holybible.navigation

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.app.ContextWrapper
import com.nevermore.sashoolya.holybible.mvvm.LanguageDialog
import com.nevermore.sashoolya.holybible.mvvm.SectionsFragment
import com.nevermore.sashoolya.holybible.util.isVisibleOrGone
import com.nevermore.sashoolya.holybible.util.provider
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_root.*
import kotlinx.android.synthetic.main.toolbar.*
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import java.util.*

class RootActivity : AppCompatActivity(){

    private val navigator = RootNavigator(this)
    val router = provider.rootRouter
    val navigatorHolder = provider.rootNavigatorHolder


    val subs = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)
        setupToolbar()
        setupNavigationMenu()
        observeLangChange()

        provider.timer.start(3)
        if (savedInstanceState == null) {
            isPreview(provider.timer.isStarted)
        }
        subs.add(provider.timer.onComplite.subscribe({
            if (it!!) {
                LanguageDialog().show(supportFragmentManager, "lang")
                isPreview(false)
            }
        }, {}))
    }

    override fun attachBaseContext(newBase: Context) {
        val context = ContextWrapper.wrap(newBase, provider.langManager.getLocale())
        super.attachBaseContext(context)
    }

    private fun setupToolbar() {
        toolbar.apply {
            title = resources.getString(R.string.app_name)
            setSupportActionBar(this)
            adaptMenuIcon()
            setNavigationOnClickListener {
                if (provider.isMenuState)
                    drawerLayout.openDrawer(GravityCompat.START)
                else
                    router.exit()
            }
        }
    }

    private fun setupNavigationMenu() {
        navigation.apply {
            setNavigationItemSelectedListener { menuItem ->
                menuItem.isChecked = true
                drawerLayout.closeDrawers()
                when (menuItem.itemId) {
                    R.id.nav_lang -> LanguageDialog().show(supportFragmentManager, "lang")
                    R.id.nav_about -> {
                        router.navigateTo(RootScreens.ABOUT_SCREEN)
                        provider.isMenuState = false
                        adaptMenuIcon()
                    }
                    R.id.nav_exit -> System.exit(0)
                }
                false
            }
        }
    }

    private fun observeLangChange(){
        subs.add(provider.langManager.langHasChanged.subscribe ({
            recreate()
        },{
            it.printStackTrace()
        }))
    }

    private fun isPreview(b: Boolean) {
        isMenuEnabled(!b)
        if (b)
            navigator.applyCommands(arrayOf<Command>(Replace(RootScreens.PREVIEW_SCREEN, null)))
        else
            navigator.applyCommands(arrayOf<Command>(Replace(RootScreens.SECTIONS_SCREEN, null)))
    }

    private fun isMenuEnabled(b: Boolean) {
        drawerLayout.isEnabled = b
        toolbar.isVisibleOrGone(b)
    }


    fun adaptState(fragment: Fragment) {
        provider.isMenuState = fragment is SectionsFragment
        adaptMenuIcon()
    }

    private fun adaptMenuIcon() {
        toolbar.setNavigationIcon(if (provider.isMenuState) R.drawable.ic_menu else R.drawable.ic_back)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onResume() {
        super.onResume()
        provider.timer.resume()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
        provider.timer.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        subs.dispose()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers()
        } else {
            router.exit()
        }
    }
}
