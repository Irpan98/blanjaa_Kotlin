package id.itborneo.blanjaa.core.ui.adapter

import android.annotation.SuppressLint
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
import id.itborneo.blanjaa.core.data.model.ProductModel
import id.itborneo.blanjaa.core.utils.extention.toRupiah
import kotlinx.android.synthetic.main.item_product.view.*


class ProductAdapter
    (private val listener: (ProductModel) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private val TAG = "ProductAdapter"
    private var listData = listOf<ProductModel>()


    fun setProducts(data: List<ProductModel>) {
        this.listData = data
        Log.d(TAG, this.listData.toString())
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(dataItem: ProductModel) {

            itemView.apply {

                tvProductName.text = dataItem.name
                tvPrice.text = dataItem.price.toRupiah()

                Glide.with(context)
                    .load(dataItem.imagePath)
                    .transform(CenterCrop(), RoundedCorners(18))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivProduct)

            }



            itemView.setOnClickListener {
                listener(dataItem)
            }

        }

    }


}