package id.itborneo.blanjaa.wishlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.core.data.model.WishlistModel
import id.itborneo.blanjaa.core.utils.extention.toRupiah
import kotlinx.android.synthetic.main.item_product.view.*
import kotlinx.android.synthetic.main.item_product.view.tvProductName
import kotlinx.android.synthetic.main.item_wish.view.*
import kotlinx.android.synthetic.main.item_wish.view.tvPrice


class WishlistAdapter
    (private val listener: (WishlistModel) -> Unit) :
    RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    private val TAG = "ProductAdapter"
    private var listData = listOf<WishlistModel>()


    fun setWishlist(data: List<WishlistModel>) {
        this.listData = data
        Log.d(TAG, this.listData.toString())
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wish, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(dataItem: WishlistModel) {

            itemView.apply {

                tvProductName.text = dataItem.nameProduct
                tvPrice.text = dataItem.priceProduct.toRupiah()

                Glide.with(context)
                    .load(dataItem.imageProduct)
                    .transform(CenterCrop(), RoundedCorners(18))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(itemView.ivWish)

            }

            itemView.setOnClickListener {
                listener(dataItem)
            }

        }

    }

}