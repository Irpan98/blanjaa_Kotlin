package id.itborneo.blanjaa.core.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ProductEntity(

    @ColumnInfo(name = "name")
    val name: String = "",


    @ColumnInfo(name = "price")
    val price: String,

    @ColumnInfo(name = "locationLng")
    val categoryId: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "imagePath")
    val imagePath: String,

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String

)