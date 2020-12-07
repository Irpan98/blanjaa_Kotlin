package id.itborneo.blanjaa.core.ui.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.itborneo.blanjaa.app.AppViewModel
import id.itborneo.blanjaa.core.repository.ThisRepository
import id.itborneo.blanjaa.detail.DetailViewModel
import id.itborneo.blanjaa.di.Injection
import id.itborneo.blanjaa.explorer.ExploreViewModel
import id.itborneo.blanjaa.login.LoginViewModel
import id.itborneo.blanjaa.register.RegisterViewModel

class ViewModelFactory private constructor(private val repo: ThisRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(
                    Injection.provideRepository(context)
                )
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(AppViewModel::class.java) -> {
                AppViewModel(repo) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repo) as T
            }
            modelClass.isAssignableFrom(ExploreViewModel::class.java) -> {
                ExploreViewModel(repo) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repo) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repo) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}