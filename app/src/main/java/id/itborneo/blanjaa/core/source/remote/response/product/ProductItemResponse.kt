package id.itborneo.blanjaa.core.source.remote.response.product


import com.google.gson.annotations.SerializedName

data class ProductItemResponse(
    @SerializedName("categori_id")
    val categoriId: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("imagePath")
    val imagePath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String
)