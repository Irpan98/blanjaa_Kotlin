package id.itborneo.blanjaa.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.iid.FirebaseInstanceId
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.core.data.model.UserModel
import id.itborneo.blanjaa.core.ui.validation.NullChecker
import id.itborneo.blanjaa.core.ui.viewModel.ViewModelFactory
import id.itborneo.blanjaa.core.utils.ui.SpinKitUtils
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    private val TAG = "RegisterFragment"
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initButtonListener()
        getToken()
    }

    private fun initButtonListener() {

        btnRegister.setOnClickListener {
            register()
        }
    }

    private fun isInputValid(): Boolean {

        val isNameValid =
            NullChecker(requireContext()).isInputValid(etName, "Nama tidak boleh kosong")

        if (!isNameValid) return false

        val isEmailValid =
            NullChecker(requireContext()).isInputValid(etEmail, "Email tidak boleh kosong")
        if (!isEmailValid) return false

        val ispasswordValid =
            NullChecker(requireContext()).isInputValid(etPassword, "Password tidak boleh kosong")
        if (!ispasswordValid) return false


        return true


    }

    private fun loading(showLoading: Boolean = true) {
        if (showLoading) {
            SpinKitUtils.show(spinKitLoading)
        } else {
            SpinKitUtils.hide(spinKitLoading)
        }
    }

    private fun register() {


        if (!isInputValid()) return
        loading()

        val user = UserModel(
            etName.text.toString(),
            etEmail.text.toString(),
            etPassword.text.toString(),
            token = token ?: ""
        )


        viewModel.register(user).observe(viewLifecycleOwner, { response ->
            loading(false)

            val getUser = response.data

            if (getUser != null) {
                if (getUser.message == "Berhasil Register") {
//                    setLastLogin(email, password)
                    //password benar

                    Toast.makeText(requireContext(), getUser.message, Toast.LENGTH_LONG)
                        .show()

                    requireActivity().onBackPressed()

                } else {
                    //password salah
                    Log.d(TAG, "register gagal")
//                    showToastSalahEmailOrPassword()

                }
            } else {
                Log.d(TAG, "register gagal")
//                showToastSalahEmailOrPassword()
                //user tidak ada
            }
        })
    }

    private fun initViewModel() {

        val factory = ViewModelFactory.getInstance(requireActivity())

        viewModel =
            ViewModelProvider(this, factory)[RegisterViewModel::class.java]
    }

    private var token: String? = null

    private fun getToken() {
        @Suppress("DEPRECATION")
        token = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "getToken $token")
    }


}