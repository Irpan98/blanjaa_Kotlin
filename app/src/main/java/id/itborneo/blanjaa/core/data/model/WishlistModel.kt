package id.itborneo.blanjaa.core.data.model

data class WishlistModel(
    val id: String,
    val productId: String,
    val userId: String,
    val nameProduct: String = "",
    val imageProduct: String = "",
    val priceProduct: String = "",

    )