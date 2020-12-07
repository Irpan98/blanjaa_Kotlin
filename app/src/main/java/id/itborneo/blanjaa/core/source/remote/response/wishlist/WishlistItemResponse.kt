package id.itborneo.blanjaa.core.source.remote.response.wishlist


import com.google.gson.annotations.SerializedName

data class WishlistItemResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("product_id")
    val productId: String,
    @SerializedName("user_id")
    val userId: String
)