package com.nevermore.sashoolya.holybible.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.nevermore.sashoolya.holybible.R
import com.nevermore.sashoolya.holybible.mvvm.*
import kotlin.system.exitProcess

class RootNavigator(activity: RootActivity) : AppNavigator(activity, R.id.container) {

    fun exit() {
        activity.finish()
        exitProcess(0)
    }

    fun showSystemMessage(message: String?) {
        Toast.makeText(activity, message ?: "null", Toast.LENGTH_SHORT).show()
    }

    fun createFragment(screenKey: String, data: Any?): Fragment? {
        when (screenKey) {
            RootScreens.SECTIONS_SCREEN -> return SectionsFragment()
            RootScreens.EXPOSITIONS_SCREEN -> return ExpositionsFragment()
            RootScreens.EXPONATE_SCREEN -> return ExponateFragment()
            RootScreens.PREVIEW_SCREEN -> return PreviewFragment()
            RootScreens.ABOUT_SCREEN -> return AboutUsFragment()

        }
        return null
    }

    fun setupFragmentTransactionAnimation(command: Command,
                                                   currentFragment: Fragment?,
                                                   nextFragment: Fragment,
                                                   fragmentTransaction: FragmentTransaction
    ) {
        currentFragment?.let {
            fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
        }
    }
}
