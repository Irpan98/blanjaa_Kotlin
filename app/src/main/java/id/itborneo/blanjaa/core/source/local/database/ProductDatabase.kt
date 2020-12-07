package id.itborneo.blanjaa.core.source.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.itborneo.blanjaa.core.source.local.dao.ProductDao
import id.itborneo.blanjaa.core.source.local.entity.ProductEntity


@Database(version = 1, entities = [ProductEntity::class])
abstract class ProductDatabase : RoomDatabase() {

    abstract fun dao(): ProductDao


    companion object {
        private var INSTANCE: ProductDatabase? = null

        @JvmStatic
        fun instance(context: Context): ProductDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ProductDatabase::class.java, "db_product"
                ).build()
            }
            return INSTANCE as ProductDatabase
        }
    }

}
