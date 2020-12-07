package id.itborneo.blanjaa.core.source.remote.response.login


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("email")
    val email: String,
    @SerializedName("expireAt")
    val expireAt: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("token")
    val token: String
)