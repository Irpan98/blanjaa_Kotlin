package id.itborneo.blanjaa.core.repository


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import id.itborneo.blanjaa.core.data.model.AddWishModel
import id.itborneo.blanjaa.core.data.model.UserModel
import id.itborneo.blanjaa.core.source.local.LocalDataSource
import id.itborneo.blanjaa.core.source.local.entity.ProductEntity
import id.itborneo.blanjaa.core.source.remote.RemoteDataSource
import id.itborneo.blanjaa.core.source.remote.response.history.AddHistory
import id.itborneo.blanjaa.core.source.remote.response.product.ProductItemResponse
import id.itborneo.blanjaa.core.utils.mapperUtils.DataMapper
import id.itborneo.blanjaa.core.utils.network.AppExecutors

class ThisRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) {

    private val TAG = "ThisRepository"

    companion object {
        @Volatile
        private var instance: ThisRepository? = null


        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ) =
            instance ?: synchronized(this)
            {
                instance ?: ThisRepository(
                    remoteData,
                    localData,
                    appExecutors
                )
            }
    }

    fun getAllCategory() = remoteDataSource.getAllCategory()

    fun getBestProduct() = remoteDataSource.getAllBestProduct()


//    fun getAllProduct() = remoteDataSource.getAllProduct()

    fun getAllProduct() =
        object :
            NetworkBoundResource<List<ProductEntity>, List<ProductItemResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<ProductEntity>> {
                return localDataSource.getProducts()
            }

            override fun shouldFetch(data: List<ProductEntity>?): Boolean {
                Log.d(TAG, "shouldFetch: ")
//                return data == null || data.isEmpty()
                return true
            }

            override fun saveCallResult(data: List<ProductItemResponse>) {
                Log.d(TAG, "saveCallResult: ")

                localDataSource.insertProducts(DataMapper.productResponseToEntity(data))
            }

            override fun createCall(): LiveData<Resource<List<ProductItemResponse>>> {
                Log.d(TAG, "createCall: ")

                return LiveDataReactiveStreams.fromPublisher(remoteDataSource.getAllProduct())
            }


        }.asLiveData()


    fun getAllWishlist() = remoteDataSource.getAllWishlist()
    fun addWishItem(addWishItem: AddWishModel) = remoteDataSource.addWishItem(addWishItem)
    fun removeWishItem(id: String) = remoteDataSource.removeWishItem(id)


    fun getAllCheckout() = remoteDataSource.getAllCheckout()
    fun addCheckoutItem(addWishItem: AddWishModel) = remoteDataSource.addCheckoutItem(addWishItem)
    fun removeCheckoutItem(id: String) = remoteDataSource.removeCheckoutItem(id)


    fun getAllHistory() = remoteDataSource.getAllHistory()
    fun addHistory(addWishItem: AddHistory) = remoteDataSource.addHistory(addWishItem)
//    fun removeHistory(id: String) = remoteDataSource.removeHistory(id)


    fun login(userModel: UserModel) = remoteDataSource.login(userModel)
    fun register(userModel: UserModel) = remoteDataSource.register(userModel)

    fun serverCheck() = remoteDataSource.checkServer()

}