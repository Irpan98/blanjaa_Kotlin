package id.itborneo.blanjaa.core.source.remote.response.category


import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("data")
    val `data`: List<CategoryItemResponse>,
    @SerializedName("errors")
    val errors: Any,
    @SerializedName("isSuccess")
    val isSuccess: Boolean,
    @SerializedName("message")
    val message: String
)