package id.itborneo.blanjaa.core.source.remote.response.bestProduct


import com.google.gson.annotations.SerializedName

data class BestProductItemResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("product_id")
    val productId: String
)