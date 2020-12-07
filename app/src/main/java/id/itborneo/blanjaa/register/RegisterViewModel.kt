package id.itborneo.blanjaa.register

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import id.itborneo.blanjaa.core.data.model.UserModel
import id.itborneo.blanjaa.core.repository.ThisRepository

class RegisterViewModel(private val repo: ThisRepository) : ViewModel() {


    fun register(user: UserModel) = LiveDataReactiveStreams.fromPublisher(repo.register(user))


}