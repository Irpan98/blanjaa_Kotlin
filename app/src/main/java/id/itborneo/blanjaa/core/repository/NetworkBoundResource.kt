package id.itborneo.blanjaa.core.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import id.itborneo.blanjaa.core.utils.network.AppExecutors


abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {
    private val TAG = "NetworkBoundResource"
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.Loading(null)
        Log.d(TAG, ": init called")
        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data ->
            Log.d(TAG, ":  result.addSource(dbSource) called")

            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(dbSource) { newData ->
                    result.value = Resource.Success(newData)
                }
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<Resource<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        Log.d(TAG, ":  fetchFromNetwork called")

        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            Log.d(TAG, ":  result.addSource(dbSource) called")

            result.value = Resource.Loading(newData)
        }
        result.addSource(apiResponse) { response ->
            Log.d(TAG, ":    result.addSource(apiResponse called")

            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is Resource.Success ->
                    mExecutors.diskIO().execute {
                        response.data?.let { saveCallResult(it) }
                        mExecutors.mainThread().execute {
                            result.addSource(loadFromDB()) { newData ->
                                result.value = Resource.Success(newData)
                            }
                        }
                    }
//                is Resource.Empty -> mExecutors.mainThread().execute {
//                    result.addSource(loadFromDB()) { newData ->
//                        result.value = Resource.Success(newData)
//                    }
//                }
                is Resource.Error -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value =
                            Resource.Error(response.message ?: " No Error message", newData)
                    }
                }

            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> = result
}