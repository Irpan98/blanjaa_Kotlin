package id.itborneo.blanjaa.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.core.data.model.UserModel
import id.itborneo.blanjaa.core.ui.parent.FragmentWithNav
import id.itborneo.blanjaa.core.ui.validation.NullChecker
import id.itborneo.blanjaa.core.ui.viewModel.ViewModelFactory
import id.itborneo.blanjaa.core.utils.constant.EXTRA_USER
import id.itborneo.blanjaa.core.utils.dialog.DialogUtls
import id.itborneo.blanjaa.core.utils.network.NetworkUtils
import id.itborneo.blanjaa.core.utils.toastUtils.ToastTop
import id.itborneo.blanjaa.core.utils.ui.SpinKitUtils
import id.itborneo.blanjaa.core.utils.user.Login
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : FragmentWithNav() {

    private lateinit var viewModel: LoginViewModel

    private val TAG = "LoginFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initButtonListener()
        checkLastLogin()
    }

    private fun isInputValid(): Boolean {

        val isEmailValid =
            NullChecker(requireContext()).isInputValid(etEmail, "Email tidak boleh kosong")

        if (!isEmailValid) return false


        val ispasswordValid =
            NullChecker(requireContext()).isInputValid(etPassword, "Password tidak boleh kosong")
        if (!ispasswordValid) return false


        return !(!isEmailValid || !ispasswordValid)


    }

    private fun initButtonListener() {
        btnLogin.setOnClickListener {
            if (!isInternetAvailable()) return@setOnClickListener
            if (!isInputValid()) return@setOnClickListener

            loading()


            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            loginValidation(email, password)

        }

        btnRegister.setOnClickListener {
            navActionRegister()
        }
    }

    private fun loading(showLoading: Boolean = true) {
        if (showLoading) {
            SpinKitUtils.show(spinKitLoading)
        } else {
            SpinKitUtils.hide(spinKitLoading)
        }
    }

    private fun loginValidation(email: String, password: String) {

        Log.d(TAG, "lloginValidation $email dan $password")

//        spinKitLoading.visibility = View.VISIBLE

        val user = UserModel(
            "",
            email,
            password
        )


        viewModel.login(user).observe(viewLifecycleOwner, { response ->
//            spinKitLoading.visibility = View.GONE
            loading(false)

            val getUser = response.data

            if (getUser != null) {
                if (getUser.message == "Berhasil login") {
                    //password benar

                    val user = UserModel(
                        id = getUser.id,
                        name = getUser.name,
                        password = etPassword.text.toString(),
                        email = etEmail.text.toString()
                    )

                    Login.setLastLogin(requireContext(), user)


                    navActionUser(user)
                    requireActivity().finish()

                } else {
                    //password salah
                    Log.d(TAG, "user password salah")
                    ToastTop.show(requireContext(), "Email atau Password Salah")

                }
            } else {

                Log.d(TAG, "user email salah")
                ToastTop.show(requireContext(), "Email atau Password Salah")
//                showToastSalahEmailOrPassword()
                //user tidak ada
            }
        })
    }

    private fun navActionUser(userModel: UserModel) {

        val bundle = bundleOf(
            EXTRA_USER to userModel,
        )
        navController.navigate(
            R.id.action_loginFragment_to_appActivity,
            bundle
        )

    }

    private fun navActionRegister() {


        navController.navigate(
            R.id.action_loginFragment_to_registerFragment,

            )

    }

    private fun initViewModel() {

        val factory = ViewModelFactory.getInstance(requireActivity())

        viewModel =
            ViewModelProvider(this, factory)[LoginViewModel::class.java]
    }

    private fun checkLastLogin() {

        val user = Login.getLastUser(requireContext())
        if (user != null) {
            navActionUser(user)

        }


    }

    private fun isInternetAvailable(): Boolean {
        return if (NetworkUtils(requireContext()).isInternetAvailable()) {
            DialogUtls(requireContext()).internetAvailable()
            true
        } else {
            DialogUtls(requireContext()).setDialogNoInternet {
                isInternetAvailable()
            }
            false
        }
    }


}


