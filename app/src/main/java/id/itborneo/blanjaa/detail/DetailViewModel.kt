package id.itborneo.blanjaa.detail

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import id.itborneo.blanjaa.core.data.model.AddWishModel
import id.itborneo.blanjaa.core.data.model.UserModel
import id.itborneo.blanjaa.core.data.model.WishlistModel
import id.itborneo.blanjaa.core.repository.ThisRepository

class DetailViewModel(private val repo: ThisRepository) : ViewModel() {

    lateinit var user: UserModel

    lateinit var listWishlist: List<WishlistModel>
    private val refreshWishlist = MutableLiveData<Unit>()
    fun refreshWishList() {
        refreshWishlist.value = Unit
    }

    fun getAllWishlist() =

        Transformations.switchMap(refreshWishlist) {
            LiveDataReactiveStreams.fromPublisher(
                repo.getAllWishlist()
            )
        }

    fun addWishItem(wishIitem: AddWishModel) =
        LiveDataReactiveStreams.fromPublisher(repo.addWishItem(wishIitem))

    fun removeWishItem(id: String) = LiveDataReactiveStreams.fromPublisher(repo.removeWishItem(id))


    lateinit var listCheckout: List<WishlistModel>
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

    fun addCheckoutItem(wishIitem: AddWishModel) =
        LiveDataReactiveStreams.fromPublisher(repo.addCheckoutItem(wishIitem))

    fun removeCheckoutItem(id: String) =
        LiveDataReactiveStreams.fromPublisher(repo.removeCheckoutItem(id))

}
