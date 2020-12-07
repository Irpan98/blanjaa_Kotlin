package id.itborneo.blanjaa.core.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductModel(
    val id: String,
    val name: String,
    val description: String,
    val price: String,
    val categoryId: String,
    val imagePath: String
) : Parcelable