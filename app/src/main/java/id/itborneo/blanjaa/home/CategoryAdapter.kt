package id.itborneo.blanjaa.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.core.source.remote.response.category.CategoryItemResponse
import kotlinx.android.synthetic.main.item_category.view.*


class CategoryAdapter
    (private val listener: (CategoryItemResponse) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private val TAG = "CategoryAdapter"
    private var listData = listOf<CategoryItemResponse>()


    fun setCategory(data: List<CategoryItemResponse>) {
        this.listData = data
        Log.d(TAG, this.listData.toString())
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(dataItem: CategoryItemResponse) {

            itemView.apply {
                tvCategory.text = dataItem.name

                Glide.with(context)
                    .load(R.drawable.background)
                    .transform(CenterCrop(), RoundedCorners(18))
                    .into(ivBackground)

            }



            itemView.setOnClickListener {
                listener(dataItem)
            }

        }

    }

}