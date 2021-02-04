package id.itborneo.blanjaa.login

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import id.itborneo.blanjaa.core.data.model.UserModel
import id.itborneo.blanjaa.core.repository.ThisRepository

class LoginViewModel(private val repo: ThisRepository) : ViewModel() {


    fun login(user: UserModel) = LiveDataReactiveStreams.fromPublisher(repo.login(user))

    fun serverCheck() = LiveDataReactiveStreams.fromPublisher(repo.serverCheck())

}