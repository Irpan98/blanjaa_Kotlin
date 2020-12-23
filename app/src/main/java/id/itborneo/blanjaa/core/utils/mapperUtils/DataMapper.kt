package id.itborneo.blanjaa.core.utils.mapperUtils

import id.itborneo.blanjaa.core.data.model.HistoryModel
import id.itborneo.blanjaa.core.data.model.ProductModel
import id.itborneo.blanjaa.core.data.model.WishlistModel
import id.itborneo.blanjaa.core.source.local.entity.ProductEntity
import id.itborneo.blanjaa.core.source.remote.response.history.HistoryResponseItem
import id.itborneo.blanjaa.core.source.remote.response.product.ProductItemResponse
import id.itborneo.blanjaa.core.source.remote.response.wishlist.WishlistItemResponse


object DataMapper {
    fun productResponseToEntity(input: List<ProductItemResponse>): List<ProductEntity> {
        val list = ArrayList<ProductEntity>()
        input.map {
            val data = ProductEntity(
                id = it.id,
                name = it.name,
                description = it.description,
                price = it.price,
                categoryId = it.categoriId,
                imagePath = it.imagePath
            )
            list.add(data)
        }
        return list
    }

    fun productEntityToModel(input: List<ProductEntity>): List<ProductModel> {
        val list = ArrayList<ProductModel>()
        input.map {
            val data = ProductModel(
                id = it.id,
                name = it.name,
                description = it.description,
                price = it.price,
                categoryId = it.categoryId,
                imagePath = it.imagePath
            )
            list.add(data)
        }
        return list
    }


    fun WishResponseToModel(
        input: List<WishlistItemResponse>,
        listProduct: List<ProductModel>
    ): List<WishlistModel> {
        val list = ArrayList<WishlistModel>()



        input.map {

            var name = ""
            var image = ""
            var price = ""
            listProduct.forEach { product ->
                if (product.id == it.productId) {
                    name = product.name
                    image = product.imagePath
                    price = product.price
                    return@forEach
                }
            }

            val item = WishlistModel(
                id = it.id,
                productId = it.productId,
                userId = it.userId,
                name,
                image,
                price
            )
            list.add(item)
        }
        return list
    }

    fun AllWishToUserWish(input: List<WishlistModel>, userId: String): List<WishlistModel> {
        val list = ArrayList<WishlistModel>()

        input.forEach {
            if (userId == it.userId) {
                list.add(it)
            }
        }

        return list
    }

    fun HistoryResponseToModel(
        input: List<HistoryResponseItem>,
        listProduct: List<ProductModel>,
        userId: String
    ): List<HistoryModel> {
        val list = ArrayList<HistoryModel>()



        input.map {
            if (it.userId == userId) {


                var name = ""
                var image = ""
                var price = ""
                listProduct.forEach { product ->
                    if (product.id == it.productId) {
                        name = product.name
                        image = product.imagePath
                        price = product.price
                        return@forEach
                    }
                }

                val item = HistoryModel(
                    id = it.id,
                    productId = it.productId,
                    userId = it.userId,
                    name,
                    image,
                    price,
                    it.payment,
                    it.date
                )
                list.add(item)
            }
        }
        return list
    }
}