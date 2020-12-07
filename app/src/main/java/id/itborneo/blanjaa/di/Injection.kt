package id.itborneo.blanjaa.di

import android.content.Context
import id.itborneo.blanjaa.core.repository.ThisRepository
import id.itborneo.blanjaa.core.source.local.LocalDataSource
import id.itborneo.blanjaa.core.source.local.database.ProductDatabase
import id.itborneo.blanjaa.core.source.remote.RemoteDataSource
import id.itborneo.blanjaa.core.source.remote.network.ApiConfig
import id.itborneo.blanjaa.core.utils.network.AppExecutors


object Injection {
    fun provideRepository(context: Context): ThisRepository {
        val database = ProductDatabase.instance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.dao())
        val appExecutors = AppExecutors()

        return ThisRepository.getInstance(
            remoteDataSource,
            localDataSource,
            appExecutors
        )
    }


}
