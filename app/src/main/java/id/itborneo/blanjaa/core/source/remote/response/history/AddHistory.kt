package id.itborneo.blanjaa.core.source.remote.response.history


import com.google.gson.annotations.SerializedName

data class AddHistory(

    @SerializedName("product_id")
    val productId: String,
    @SerializedName("user_id")
    val userId: String,
    val date: String,
    val payment: String
)