package id.itborneo.blanjaa.core.source.remote.response.category


import com.google.gson.annotations.SerializedName

data class CategoryItemResponse(
    @SerializedName("backgroundPath")
    val backgroundPath: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)