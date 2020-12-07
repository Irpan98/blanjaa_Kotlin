package id.itborneo.blanjaa.core.source.remote.response.wishlist


import com.google.gson.annotations.SerializedName

data class AddWishlistResponse(
    @SerializedName("data")
    val `data`: AddWishlistData,
    @SerializedName("errors")
    val errors: Any,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String
)