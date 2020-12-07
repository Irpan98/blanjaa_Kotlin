package id.itborneo.blanjaa.core.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.itborneo.blanjaa.core.source.local.entity.ProductEntity
import io.reactivex.Flowable

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertsAll(wiplace: List<ProductEntity>)


    @Query("SELECT * FROM ProductEntity")
    fun getAll(): Flowable<List<ProductEntity>>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertOnePlace(wiplace: WiPlaceEntity): Long


//    @Update(entity = WiPlaceEntity::class, onConflict = OnConflictStrategy.REPLACE)
//    fun editPlace(item: WiPlaceEntity): Int
//
//    @Delete
//    fun removePlace(item: WiPlaceEntity): Int
}