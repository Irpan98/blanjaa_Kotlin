package id.itborneo.blanjaa.core.ui.parent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation

open class FragmentWithNav : Fragment() {

    open lateinit var navController: NavController


    private fun initNav(view: View) {
        navController = Navigation.findNavController(view)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNav(view)

    }
}