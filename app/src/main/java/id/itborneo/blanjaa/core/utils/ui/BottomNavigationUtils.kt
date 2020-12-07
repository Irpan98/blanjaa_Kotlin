package id.itborneo.blanjaa.core.utils.ui

import android.app.Activity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.itborneo.blanjaa.R

object BottomNavigationUtils {
    fun initBottomNav(
        activity: AppCompatActivity,
        navhostFragment: Int,
        bottomNavigationView: BottomNavigationView
    ) {
        val navController = Navigation.findNavController(activity, navhostFragment)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    fun visible(activity: Activity?) {
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)?.visibility =
            View.VISIBLE
    }

    fun invisible(activity: Activity?) {
        activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)?.visibility =
            View.GONE
    }
}