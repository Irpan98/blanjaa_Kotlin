package id.itborneo.blanjaa.core.ui.parent

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import id.itborneo.blanjaa.app.AppViewModel
import id.itborneo.blanjaa.core.ui.viewModel.ViewModelFactory
import id.itborneo.blanjaa.core.utils.ui.BottomNavigationUtils
import id.itborneo.blanjaa.core.utils.ui.SpinKitUtils
import kotlinx.android.synthetic.main.fragment_register.*

open class FragmentWithViewModelandNav : FragmentWithNav() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        visibleBottomNav()
        initViewModel()

    }

    open lateinit var viewModel: AppViewModel


    private fun initViewModel() {

        val factory = ViewModelFactory.getInstance(requireActivity())

        viewModel = activity?.run {
            ViewModelProvider(this, factory)[AppViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

    }


    private fun visibleBottomNav() {
        BottomNavigationUtils.visible(activity)
    }

    open fun loading(showLoading: Boolean = true) {
        if (showLoading) {
            SpinKitUtils.show(spinKitLoading)
        } else {
            SpinKitUtils.hide(spinKitLoading)
        }
    }

}