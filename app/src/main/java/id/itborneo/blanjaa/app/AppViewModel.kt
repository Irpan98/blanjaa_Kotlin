package id.itborneo.blanjaa.app

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import id.itborneo.blanjaa.core.data.model.ProductModel
import id.itborneo.blanjaa.core.data.model.UserModel
import id.itborneo.blanjaa.core.data.model.WishlistModel
import id.itborneo.blanjaa.core.repository.ThisRepository
import id.itborneo.blanjaa.core.source.remote.response.history.AddHistory

class AppViewModel(private val repo: ThisRepository) : ViewModel() {

    lateinit var user: UserModel


    //list product
    lateinit var listProduct: List<ProductModel>
    private val refreshProduct = MutableLiveData<Unit>()
    fun refreshProduct() {
        refreshProduct.value = Unit
    }

    fun getAllProduct() =

        Transformations.switchMap(refreshProduct) {
            repo.getAllProduct()

        }

    fun getBestProduct() = LiveDataReactiveStreams.fromPublisher(repo.getBestProduct())

    fun getAllCategory() = LiveDataReactiveStreams.fromPublisher(repo.getAllCategory())

    //wishlist
    lateinit var listWishlist: List<WishlistModel>
    fun getAllWishlist() = LiveDataReactiveStreams.fromPublisher(repo.getAllWishlist())


    //    fun getAllCheckout() = LiveDataReactiveStreams.fromPublisher(repo.getAllCheckout())

    private val refreshCheckout = MutableLiveData<Unit>()
    fun refreshCheckout() {
        refreshCheckout.value = Unit
    }

    fun getAllCheckout() =

        Transformations.switchMap(refreshCheckout) {
            LiveDataReactiveStreams.fromPublisher(
                repo.getAllCheckout()
            )
        }

    fun removeCheckoutItem(id: String) =
        LiveDataReactiveStreams.fromPublisher(repo.removeCheckoutItem(id))


    fun getAllHistory() = LiveDataReactiveStreams.fromPublisher(repo.getAllHistory())
    fun addHistory(addWishItem: AddHistory) =
        LiveDataReactiveStreams.fromPublisher(repo.addHistory(addWishItem))


    val updatedproduct = MutableLiveData<Boolean>()

    //category
//    fun get
}
