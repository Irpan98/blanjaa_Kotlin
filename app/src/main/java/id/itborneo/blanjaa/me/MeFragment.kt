package id.itborneo.blanjaa.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.core.ui.parent.FragmentWithViewModelandNav
import id.itborneo.blanjaa.core.utils.user.Login
import kotlinx.android.synthetic.main.fragment_me.*
import kotlinx.android.synthetic.main.partial_appbar.*


class MeFragment : FragmentWithViewModelandNav() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonListener()
        updateUI()
        initAppbar()
    }

    private fun buttonListener() {

        btnHistory.setOnClickListener {
            navAction(R.id.action_meFragment_to_historyFragment)
        }

        btnLogout.setOnClickListener {


            Login.removeLastUser(requireContext())

            navAction(R.id.action_meFragment_to_mainActivity)
            requireActivity().finish()

        }
    }

    private fun initAppbar() {
        tbApp.title = "Me"

    }


    private fun updateUI() {
        tvName.text = viewModel.user.name
        tvEmail.text = viewModel.user.email
    }


    private fun navAction(action: Int) {

//        val bundle = bundleOf(
//            EXTRA_PRODUCT to product,
//            EXTRA_LIST_PRODUCT to viewModel.listProduct
//        )
        navController.navigate(
            action,
//            bundle
        )

    }

}