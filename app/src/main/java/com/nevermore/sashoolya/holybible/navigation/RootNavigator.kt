package com.nevermore.sashoolya.holybible.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.Command
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.mvvm.SectionsFragment

class RootNavigator(val activity : AppCompatActivity) : SupportFragmentNavigator(activity.supportFragmentManager, R.id.container) {

    override fun exit() {
        activity.finish()
    }

    override fun showSystemMessage(message: String?) {
        Toast.makeText(activity, message ?: "null", Toast.LENGTH_SHORT).show()
    }

    override fun createFragment(screenKey: String, data: Any?): Fragment? {
        when (screenKey) {
            RootScreens.SECTIONS_SCREEN -> return SectionsFragment()

        }
        return null
    }

    override fun setupFragmentTransactionAnimation(command: Command,
                                                   currentFragment: Fragment?,
                                                   nextFragment: Fragment,
                                                   fragmentTransaction: FragmentTransaction) {
        currentFragment?.let {
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
        }
    }
}
