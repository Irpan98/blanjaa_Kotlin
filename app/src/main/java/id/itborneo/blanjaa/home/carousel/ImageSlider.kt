package id.itborneo.blanjaa.home.carousel

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.core.data.model.ProductModel

class ImageSlider(
    private val context: Context,
    private val viewPager: ViewPager,
    private val sliderDots: LinearLayout,
) {

    private val TAG = "ImageSlider"
    fun initialize(
        data: List<ProductModel>, listener: (ProductModel) -> Unit
    ) {
        val viewPager = viewPager as ViewPager?

        Log.d(TAG, "initialize" + data.toString())
        val sliderDotspanel = sliderDots as LinearLayout?
        val viewPagerAdapter = ViewPagerAdapter(context, data, listener)
        viewPager?.adapter = viewPagerAdapter
        val dotscount = data.size
        val dots = mutableListOf<ImageView>()
        for (i in 0 until dotscount) {
            val img = ImageView(context)
            img.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.nonactive_dot
                )
            )
            dots.add(img)

            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            sliderDotspanel?.addView(dots.get(i), params)

        }


        Log.d("dots", dots[0].toString())
        dots[0].setImageDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.active_dot
            )
        )

        viewPager?.addOnPageChangeListener(
            object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {
                    for (i in 0 until dotscount) {
                        dots[i]?.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.nonactive_dot
                            )
                        )
                    }
                    dots[position]?.setImageDrawable(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.active_dot
                        )
                    )
                }

                override fun onPageScrollStateChanged(state: Int) {}
            })
    }


}