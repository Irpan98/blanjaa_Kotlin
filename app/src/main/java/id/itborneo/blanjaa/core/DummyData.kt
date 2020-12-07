package id.itborneo.blanjaa.core

import id.itborneo.blanjaa.core.data.model.ProductModel

object DummyData {

    fun generateProducts(): List<ProductModel> {

        val listProduct = mutableListOf<ProductModel>()

        listProduct.add(
            ProductModel(
                "1",
                "Laptop ACS",
                "laptop bagus untuk dipake",
                "45000000",
                "Laptop",
                ""
            )
        )
        listProduct.add(
            ProductModel(
                "2",
                "Laptop 2",
                "laptop bagus untuk dipake",
                "45000000",
                "Laptop",
                ""
            )
        )

        return listProduct
    }
}