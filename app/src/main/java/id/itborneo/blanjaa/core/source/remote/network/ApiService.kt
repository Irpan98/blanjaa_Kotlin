package id.itborneo.blanjaa.core.source.remote.network

import id.itborneo.blanjaa.core.source.remote.response.bestProduct.BestProductResponse
import id.itborneo.blanjaa.core.source.remote.response.category.CategoryResponse
import id.itborneo.blanjaa.core.source.remote.response.history.HistoryResponse
import id.itborneo.blanjaa.core.source.remote.response.login.LoginResponse
import id.itborneo.blanjaa.core.source.remote.response.product.ProductResponse
import id.itborneo.blanjaa.core.source.remote.response.register.RegisterResponse
import id.itborneo.blanjaa.core.source.remote.response.wishlist.AddWishlistResponse
import id.itborneo.blanjaa.core.source.remote.response.wishlist.RemoveWishResponse
import id.itborneo.blanjaa.core.source.remote.response.wishlist.WishlistResponse
import io.reactivex.Flowable
import retrofit2.http.*


interface ApiService {


    //category
    @GET("categories")
    fun getListCategory(): Flowable<CategoryResponse>


    //product
    @GET("products")
    fun getListProduct(): Flowable<ProductResponse>

    //best_product
    @GET("BestProduct")
    fun getBestProduct(): Flowable<BestProductResponse>


    //wishlist
    @GET("WishLists")
    fun getAllWishlist(): Flowable<WishlistResponse>


    @FormUrlEncoded
    @POST("wishlists/create")
    fun addWishItem(
        @Field("product_id") product_id: String,
        @Field("user_id") user_id: String,
    ): Flowable<AddWishlistResponse>


    @DELETE("wishlists/{id}")
    fun removeWishItem(
        @Path(
            "id"
        )
        id: String,
    ): Flowable<RemoveWishResponse>


    //checkout
    @GET("checkouts")
    fun getAllCheckout(): Flowable<WishlistResponse>


    @FormUrlEncoded
    @POST("checkouts/create")
    fun addCheckout(
        @Field("product_id") product_id: String,
        @Field("user_id") user_id: String,
    ): Flowable<AddWishlistResponse>


    @DELETE("checkouts/{id}")
    fun removeCheckout(
        @Path(
            "id"
        )
        id: String,
    ): Flowable<RemoveWishResponse>


    //wishlist
    @GET("histories")
    fun getAllHistory(): Flowable<HistoryResponse>


    @FormUrlEncoded
    @POST("histories/create")
    fun addHistory(
        @Field("product_id") product_id: String,
        @Field("user_id") user_id: String,
        @Field("date") date: String,
        @Field("payment") payment: String,

        ): Flowable<AddWishlistResponse>


//    @DELETE("histories/{id}")
//    fun removeHistory(
//        @Path(
//            "id"
//        )
//        id: String,
//    ): Flowable<RemoveWishResponse>


    @FormUrlEncoded
    @POST("Auth/register")
    fun register(
        @Field("name") name: String,

        @Field("email") email: String,
        @Field("password") password: String,
        @Field("token") token: String
    ): Flowable<RegisterResponse>


    @FormUrlEncoded
    @POST("Auth/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Flowable<LoginResponse>
}