package id.itborneo.blanjaa.core.source.remote.response.history

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

	@field:SerializedName("data")
	val data: List<HistoryResponseItem>,

	@field:SerializedName("message")
	val message: String = "",

	@field:SerializedName("errors")
	val errors: Any? = null,

	@field:SerializedName("isSuccess")
	val isSuccess: Boolean? = null
)

data class HistoryResponseItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("product_id")
	val productId: String? = null,

	@field:SerializedName("payment")
	val payment: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
