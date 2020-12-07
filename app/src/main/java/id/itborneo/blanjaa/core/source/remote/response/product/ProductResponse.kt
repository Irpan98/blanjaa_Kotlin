package id.itborneo.blanjaa.core.source.remote.response.product


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("data")
    val `data`: List<ProductItemResponse>,
    @SerializedName("errors")
    val errors: Any,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String
)