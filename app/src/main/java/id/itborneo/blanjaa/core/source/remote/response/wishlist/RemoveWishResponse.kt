package id.itborneo.blanjaa.core.source.remote.response.wishlist


import com.google.gson.annotations.SerializedName

data class RemoveWishResponse(
    @SerializedName("data")
    val `data`: Any,
    @SerializedName("errors")
    val errors: Any,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String
)