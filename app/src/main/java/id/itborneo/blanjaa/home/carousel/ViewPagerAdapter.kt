package id.itborneo.blanjaa.home.carousel

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.core.data.model.ProductModel


//allow to slide image
class ViewPagerAdapter(
    private val context: Context,
    private val data: List<ProductModel>,
    private val listener: (ProductModel) -> Unit
) :
    PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null

    override fun getCount(): Int {
        Log.d("ViewPagerAdapter", "getCount $data")

        return data.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater!!.inflate(R.layout.item_carousel, null)
        val imageView = view.findViewById<View>(R.id.imageView) as ImageView



        Glide.with(context)
            .load(data[position].imagePath)
            .into(imageView)
//


        view.setOnClickListener {
            listener(data[position])
        }


        val vp: ViewPager = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp: ViewPager = container as ViewPager
        val view = `object` as View?
        vp.removeView(view)
    }


}
