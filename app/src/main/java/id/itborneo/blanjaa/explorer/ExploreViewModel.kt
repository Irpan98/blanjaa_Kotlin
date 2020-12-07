package id.itborneo.blanjaa.explorer

import androidx.lifecycle.ViewModel
import id.itborneo.blanjaa.core.DummyData
import id.itborneo.blanjaa.core.repository.ThisRepository

class ExploreViewModel(private val repo: ThisRepository) : ViewModel() {

    val products = DummyData.generateProducts()


}