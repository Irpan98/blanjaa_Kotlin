package id.itborneo.blanjaa.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.core.ui.theme.DayNight

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DayNight.disableDayNight()
//        initUI()
        initNav(view)
        initTimer()
    }

//    @Suppress("DEPRECATION")
//    private fun initUI() {
//
//        val UI_OPTIONS: Int =
//            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//        requireActivity().window.decorView.systemUiVisibility = UI_OPTIONS
//    }

    @Suppress("DEPRECATION")
    private fun initTimer() {
        val handler = Handler()
        handler.postDelayed({


            navAction()


        }, 3000)
    }

    lateinit var navController: NavController


    private fun initNav(view: View) {
        navController = Navigation.findNavController(view)

    }

    private fun navAction() {


        navController.navigate(
            R.id.action_splashFragment_to_loginFragment,

            )

    }


}