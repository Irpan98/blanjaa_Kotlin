package id.itborneo.blanjaa.core.source.remote.response.register


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Int
)