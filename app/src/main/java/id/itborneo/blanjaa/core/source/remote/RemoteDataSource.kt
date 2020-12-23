package id.itborneo.blanjaa.core.source.remote

import android.annotation.SuppressLint
import android.util.Log
import id.itborneo.blanjaa.core.data.model.AddWishModel
import id.itborneo.blanjaa.core.data.model.UserModel
import id.itborneo.blanjaa.core.repository.Resource
import id.itborneo.blanjaa.core.source.remote.network.ApiService
import id.itborneo.blanjaa.core.source.remote.response.bestProduct.BestProductItemResponse
import id.itborneo.blanjaa.core.source.remote.response.category.CategoryItemResponse
import id.itborneo.blanjaa.core.source.remote.response.history.AddHistory
import id.itborneo.blanjaa.core.source.remote.response.history.HistoryResponseItem
import id.itborneo.blanjaa.core.source.remote.response.login.LoginResponse
import id.itborneo.blanjaa.core.source.remote.response.product.ProductItemResponse
import id.itborneo.blanjaa.core.source.remote.response.register.RegisterResponse
import id.itborneo.blanjaa.core.source.remote.response.wishlist.AddWishlistResponse
import id.itborneo.blanjaa.core.source.remote.response.wishlist.RemoveWishResponse
import id.itborneo.blanjaa.core.source.remote.response.wishlist.WishlistItemResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource private constructor(private val apiService: ApiService) {


    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }


    @SuppressLint("CheckResult")
    fun getAllCategory(): Flowable<Resource<List<CategoryItemResponse>>> {
        val resultData = PublishSubject.create<Resource<List<CategoryItemResponse>>>()

        //get data from remote api
        val client = apiService.getListCategory()

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.data

                resultData.onNext(
                    if (dataArray.isNotEmpty()) Resource.Success(dataArray) else Resource.Error(
                        response.message
                    )
                )
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }


    @SuppressLint("CheckResult")
    fun getAllProduct(): Flowable<Resource<List<ProductItemResponse>>> {
        val resultData = PublishSubject.create<Resource<List<ProductItemResponse>>>()

        //get data from remote api
        val client = apiService.getListProduct()

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.data

                resultData.onNext(
                    if (dataArray.isNotEmpty()) Resource.Success(dataArray) else Resource.Error(
                        response.message
                    )
                )
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getAllBestProduct(): Flowable<Resource<List<BestProductItemResponse>>> {
        val resultData = PublishSubject.create<Resource<List<BestProductItemResponse>>>()

        //get data from remote api
        val client = apiService.getBestProduct()

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.data

                resultData.onNext(
                    if (dataArray.isNotEmpty()) Resource.Success(dataArray) else Resource.Error(
                        response.message
                    )
                )
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }


    @SuppressLint("CheckResult")
    fun getAllWishlist(): Flowable<Resource<List<WishlistItemResponse>>> {
        val resultData = PublishSubject.create<Resource<List<WishlistItemResponse>>>()

        //get data from remote api
        val client = apiService.getAllWishlist()

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.data

                resultData.onNext(
                    if (dataArray.isNotEmpty()) Resource.Success(dataArray) else Resource.Error(
                        response.message
                    )
                )
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun addWishItem(wishItem: AddWishModel): Flowable<Resource<AddWishlistResponse>> {
        val resultData = PublishSubject.create<Resource<AddWishlistResponse>>()

        //get data from remote api
        val client = apiService.addWishItem(wishItem.productId, wishItem.userId)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response

                resultData.onNext(
                    if (dataArray != null) Resource.Success(dataArray) else Resource.Error(
                        response.message
                    )
                )
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun removeWishItem(id: String): Flowable<Resource<RemoveWishResponse>> {
        val resultData = PublishSubject.create<Resource<RemoveWishResponse>>()

        //get data from remote api
        val client = apiService.removeWishItem(id)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response

                resultData.onNext(
                    if (dataArray != null) Resource.Success(dataArray) else Resource.Error(
                        response.message
                    )
                )
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getAllCheckout(): Flowable<Resource<List<WishlistItemResponse>>> {
        val resultData = PublishSubject.create<Resource<List<WishlistItemResponse>>>()

        //get data from remote api
        val client = apiService.getAllCheckout()

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.data

                resultData.onNext(
                    if (dataArray.isNotEmpty()) Resource.Success(dataArray) else Resource.Error(
                        response.message
                    )
                )
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun addCheckoutItem(item: AddWishModel): Flowable<Resource<AddWishlistResponse>> {
        val resultData = PublishSubject.create<Resource<AddWishlistResponse>>()

        //get data from remote api
        val client = apiService.addCheckout(item.productId, item.userId)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response

                resultData.onNext(
                    if (dataArray != null) Resource.Success(dataArray) else Resource.Error(
                        response.message
                    )
                )
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun removeCheckoutItem(id: String): Flowable<Resource<RemoveWishResponse>> {
        val resultData = PublishSubject.create<Resource<RemoveWishResponse>>()

        //get data from remote api
        val client = apiService.removeCheckout(id)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response

                resultData.onNext(
                    if (dataArray != null) Resource.Success(dataArray) else Resource.Error(
                        response.message
                    )
                )
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }


    @SuppressLint("CheckResult")
    fun getAllHistory(): Flowable<Resource<List<HistoryResponseItem>>> {
        val resultData = PublishSubject.create<Resource<List<HistoryResponseItem>>>()

        //get data from remote api
        val client = apiService.getAllHistory()

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response.data

                resultData.onNext(
                    if (dataArray.isNotEmpty()) Resource.Success(dataArray) else Resource.Error(
                        response.message
                    )
                )
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun addHistory(history: AddHistory): Flowable<Resource<AddWishlistResponse>> {
        val resultData = PublishSubject.create<Resource<AddWishlistResponse>>()

        //get data from remote api
        val client =
            apiService.addHistory(history.productId, history.userId, history.date, history.payment)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response

                resultData.onNext(
                    if (dataArray != null) Resource.Success(dataArray) else Resource.Error(
                        response.message
                    )
                )
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

//    @SuppressLint("CheckResult")
//    fun removeHistory(id: String): Flowable<Resource<RemoveWishResponse>> {
//        val resultData = PublishSubject.create<Resource<RemoveWishResponse>>()
//
//        //get data from remote api
//        val client = apiService.removeHistory(id)
//
//        client
//            .subscribeOn(Schedulers.computation())
//            .observeOn(AndroidSchedulers.mainThread())
//            .take(1)
//            .subscribe({ response ->
//                val dataArray = response
//
//                resultData.onNext(
//                    if (dataArray != null) Resource.Success(dataArray) else Resource.Error(
//                        response.message
//                    )
//                )
//            }, { error ->
//                resultData.onNext(Resource.Error(error.message.toString()))
//                Log.e("RemoteDataSource", error.toString())
//            })
//
//        return resultData.toFlowable(BackpressureStrategy.BUFFER)
//    }

    @SuppressLint("CheckResult")
    fun register(item: UserModel): Flowable<Resource<RegisterResponse>> {
        val resultData = PublishSubject.create<Resource<RegisterResponse>>()

        //get data from remote api
        val client = apiService.register(item.name, item.email, item.password, item.token)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response

                resultData.onNext(
                    if (dataArray != null) Resource.Success(dataArray) else Resource.Error(
                        response.message
                    )
                )
            }, { error ->
                var msg = "Something's Wrong"
                if (error.message == "HTTP 409 Conflict") {
                    msg = "Gagal Register, email sudah digunakan"
                }
                resultData.onNext(Resource.Error(msg))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun login(item: UserModel): Flowable<Resource<LoginResponse>> {
        val resultData = PublishSubject.create<Resource<LoginResponse>>()

        //get data from remote api
        val client = apiService.login(item.email, item.password)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe({ response ->
                val dataArray = response

                resultData.onNext(
                    if (dataArray != null) Resource.Success(dataArray) else Resource.Error(
                        response.message
                    )
                )
            }, { error ->
                resultData.onNext(Resource.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}