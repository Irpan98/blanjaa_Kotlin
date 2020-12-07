package id.itborneo.blanjaa.productByCategory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.core.data.model.ProductModel
import id.itborneo.blanjaa.core.data.model.UserModel
import id.itborneo.blanjaa.core.ui.adapter.ProductAdapter
import id.itborneo.blanjaa.core.ui.parent.FragmentWithNav
import id.itborneo.blanjaa.core.utils.constant.EXTRA_LIST_PRODUCT
import id.itborneo.blanjaa.core.utils.constant.EXTRA_PRODUCT
import id.itborneo.blanjaa.core.utils.constant.EXTRA_USER
import kotlinx.android.synthetic.main.fragment_explorer.*


class ProductByCategoryFragment : FragmentWithNav() {

    private lateinit var adapter: ProductAdapter
    private val TAG = "ExplorerFragment"
    private lateinit var user: UserModel

    private lateinit var listProduct: List<ProductModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_by_category, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecyclerView()
        getInitData()
    }

    private fun getInitData() {


        val intent2 = arguments?.getParcelableArrayList<ProductModel>(EXTRA_LIST_PRODUCT)
        val intent3 = arguments?.getParcelable<UserModel>(EXTRA_USER)


        if (intent2 != null) {
            listProduct = intent2.toList()
            Log.d(TAG, "$intent2")

            adapter.setProducts(listProduct)
        }
        if (intent3 != null) {
            user = intent3
        }

    }


    private fun navAction(product: ProductModel) {

        val bundle = bundleOf(
            EXTRA_PRODUCT to product,
            EXTRA_LIST_PRODUCT to listProduct,
            EXTRA_USER to user

        )
        navController.navigate(
            R.id.action_productByCategoryFragment_to_detailFragment,
            bundle
        )

    }

    private fun initializeRecyclerView() {
        adapter = ProductAdapter {
            navAction(it)
        }

        rvProducts.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext())
        rvProducts.layoutManager = layoutManager
    }

}