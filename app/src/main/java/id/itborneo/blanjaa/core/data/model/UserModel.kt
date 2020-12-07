package id.itborneo.blanjaa.core.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    val name: String,
    val email: String,
    val password: String,
    val id: String = "",
    val token: String = ""

) : Parcelable