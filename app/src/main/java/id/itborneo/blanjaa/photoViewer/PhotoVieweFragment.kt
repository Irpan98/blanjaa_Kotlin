package id.itborneo.blanjaa.photoViewer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.core.data.model.ProductModel
import id.itborneo.blanjaa.core.utils.constant.EXTRA_PRODUCT
import kotlinx.android.synthetic.main.fragment_photo_viewe.*


class PhotoVieweFragment : Fragment() {

    private lateinit var productModel: ProductModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_photo_viewe, container, false)
    }

    private fun initData() {

        val intentPlace = arguments?.getParcelable<ProductModel>(EXTRA_PRODUCT)
        if (intentPlace != null) {
            productModel = intentPlace
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        updateUI()
    }

    private fun updateUI() {
        Glide.with(requireContext())
            .load(productModel.imagePath)
            .into(ivPlace)
    }


}