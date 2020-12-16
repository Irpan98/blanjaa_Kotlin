package id.itborneo.blanjaa.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.core.ui.theme.DayNight
import id.itborneo.blanjaa.core.utils.ui.BottomNavigationUtils
import id.itborneo.blanjaa.core.utils.ui.ToolbarUtils
import kotlinx.android.synthetic.main.activity_app.*

class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        DayNight.disableDayNight()

        ToolbarUtils.removeActionBar(this)
        BottomNavigationUtils.initBottomNav(this, R.id.AppNavHostFragment, bottomNavigationView)
        getDataAndSendToFragment()
    }

    private fun getDataAndSendToFragment() {
        findNavController(R.id.AppNavHostFragment)
            .setGraph(R.navigation.app_nav_graph, intent.extras)
    }

}