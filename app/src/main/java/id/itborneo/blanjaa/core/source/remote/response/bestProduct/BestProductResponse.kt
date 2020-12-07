package id.itborneo.blanjaa.core.source.remote.response.bestProduct


import com.google.gson.annotations.SerializedName

data class BestProductResponse(
    @SerializedName("data")
    val `data`: List<BestProductItemResponse>,
    @SerializedName("errors")
    val errors: Any,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String
)