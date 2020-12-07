package id.itborneo.blanjaa.wishlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.core.data.model.ProductModel
import id.itborneo.blanjaa.core.ui.parent.FragmentWithViewModelandNav
import id.itborneo.blanjaa.core.utils.constant.EXTRA_LIST_PRODUCT
import id.itborneo.blanjaa.core.utils.constant.EXTRA_PRODUCT
import id.itborneo.blanjaa.core.utils.constant.EXTRA_USER
import id.itborneo.blanjaa.core.utils.mapperUtils.DataMapper
import kotlinx.android.synthetic.main.fragment_wishlist.*
import kotlinx.android.synthetic.main.partial_appbar.*


class WishlistFragment : FragmentWithViewModelandNav() {


    private val TAG = "WishlistFragment"

    private lateinit var adapter: WishlistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wishlist, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
        initAppbar()
        getInitData()
        getData()

    }

    private fun initAppbar() {
        tbApp.title = "Wishlist"

    }

    private fun getInitData() {
        val intent2 = arguments?.getParcelableArrayList<ProductModel>(EXTRA_LIST_PRODUCT)


        if (intent2 != null) {
            DataMapper
            viewModel.listProduct = intent2.toList()
            Log.d(TAG, "$intent2")

        }

        updateUI()

    }

    private fun updateUI() {

//        tvName.text = product.name


    }

    private fun getData() {
        loading()


        viewModel.getAllWishlist().observe(viewLifecycleOwner) {
            loading(false)

            Log.d(TAG, "user id ${viewModel.user}")
            Log.d(TAG, it.data.toString())

            val data = it.data
            if (data != null) {

                val allWishlist = DataMapper.WishResponseToModel(data, viewModel.listProduct)

                viewModel.listWishlist = DataMapper.AllWishToUserWish(
                    allWishlist,
                    viewModel.user.id,
                )

                Log.d(TAG, "my wishlist ${viewModel.listWishlist}")

                adapter.setWishlist(
                    viewModel.listWishlist
                )

            }


        }

    }

    private fun initializeRecyclerView() {
        adapter = WishlistAdapter { wishlist ->
//            navAction(it)

            viewModel.listProduct.forEach { product ->
                if (product.id == wishlist.productId) {
                    navAction(product)
                    return@forEach
                }

            }


        }

        rvWislist.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext())
        rvWislist.layoutManager = layoutManager
    }


    private fun navAction(product: ProductModel) {

        val bundle = bundleOf(
            EXTRA_PRODUCT to product,
            EXTRA_LIST_PRODUCT to viewModel.listProduct,
            EXTRA_USER to viewModel.user

        )
        navController.navigate(
            R.id.action_wishlistFragment_to_detailFragment,
            bundle
        )

    }


}