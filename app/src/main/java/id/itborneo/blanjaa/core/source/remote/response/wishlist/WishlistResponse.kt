package id.itborneo.blanjaa.core.source.remote.response.wishlist


import com.google.gson.annotations.SerializedName

data class WishlistResponse(
    @SerializedName("data")
    val `data`: List<WishlistItemResponse>,
    @SerializedName("errors")
    val errors: Any,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String
)