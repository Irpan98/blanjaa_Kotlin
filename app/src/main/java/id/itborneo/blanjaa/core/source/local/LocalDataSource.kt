package id.itborneo.blanjaa.core.source.local

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import id.itborneo.blanjaa.core.source.local.dao.ProductDao
import id.itborneo.blanjaa.core.source.local.entity.ProductEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LocalDataSource private constructor(private val daoProduct: ProductDao) {

    private val TAG = "LocalDataSource"

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(dao: ProductDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(dao)
    }

    fun insertProducts(places: List<ProductEntity>) {

        daoProduct.insertsAll(places)

    }

    @SuppressLint("CheckResult")
    fun getProducts(): MutableLiveData<List<ProductEntity>> {

        val result = MutableLiveData<List<ProductEntity>>()

        daoProduct.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
//                LiveDataReactiveStreams.fromPublisher(it)
                Log.d(TAG, " getPlaces ${it.size}")
                result.value = it
            }

        return result
    }


}