package com.nevermore.sashoolya.holybible.navigation

import android.content.Context
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.app.ContextWrapper
import com.nevermore.sashoolya.holybible.databinding.ActivityRootBinding
import com.nevermore.sashoolya.holybible.fragments.*
import com.nevermore.sashoolya.holybible.tools.isVisibleOrGone
import com.nevermore.sashoolya.holybible.tools.provider
import io.reactivex.disposables.CompositeDisposable
import kotlin.system.exitProcess

class RootActivity : AppCompatActivity(){

    private val navigator = RootNavigator(this)
    private val router = provider.rootRouter
    private val navigatorHolder = provider.rootNavigatorHolder
    private lateinit var mBinding: ActivityRootBinding
    private lateinit var mToolbar: Toolbar

    private val subs = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityRootBinding.inflate(layoutInflater)
        mToolbar = mBinding.toolbar
        setContentView(mBinding.root)

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
        mToolbar.apply {
            title = resources.getString(R.string.app_name)
            setSupportActionBar(this)
            adaptMenuIcon()
            setNavigationOnClickListener {
                if (provider.isMenuState)
                    mBinding.drawerLayout.openDrawer(GravityCompat.START)
                else
                    router.exit()
            }
        }
    }

    private fun setupNavigationMenu() {
        mBinding.navigation.apply {
            setNavigationItemSelectedListener { menuItem ->
                menuItem.isChecked = true
                mBinding.drawerLayout.closeDrawers()
                when (menuItem.itemId) {
                    R.id.nav_lang -> LanguageDialog().show(supportFragmentManager, "lang")
                    R.id.nav_about -> {
                        router.navigateTo(FragmentScreen{ AboutUsFragment() })
                        provider.isMenuState = false
                        adaptMenuIcon()
                    }
                    R.id.nav_exit -> exitProcess(0)
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
            navigator.applyCommands(arrayOf<Command>(Replace(FragmentScreen{ PreviewFragment() })))
        else
            navigator.applyCommands(arrayOf<Command>(Replace(FragmentScreen{ SectionsFragment() })))
    }

    private fun isMenuEnabled(b: Boolean) {
        mBinding.drawerLayout.isEnabled = b
        mToolbar.isVisibleOrGone(b)
    }


    fun adaptState(fragment: Fragment) {
        provider.isMenuState = fragment is SectionsFragment
        adaptMenuIcon()
    }

    private fun adaptMenuIcon() {
        mToolbar.setNavigationIcon(if (provider.isMenuState) R.drawable.ic_menu else R.drawable.ic_back)
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

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            mBinding.drawerLayout.closeDrawers()
        } else {
            router.exit()
        }
    }
}
