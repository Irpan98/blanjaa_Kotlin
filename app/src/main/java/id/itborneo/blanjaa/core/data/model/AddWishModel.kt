package id.itborneo.blanjaa.core.data.model

import com.google.gson.annotations.SerializedName

data class AddWishModel(
    @SerializedName("product_id")
    val productId: String,
    @SerializedName("user_id")
    val userId: String

)