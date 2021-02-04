package id.itborneo.blanjaa.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.iid.FirebaseInstanceId
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.core.data.model.ProductModel
import id.itborneo.blanjaa.core.data.model.UserModel
import id.itborneo.blanjaa.core.ui.adapter.ProductAdapter
import id.itborneo.blanjaa.core.ui.parent.FragmentWithViewModelandNav
import id.itborneo.blanjaa.core.utils.constant.EXTRA_LIST_PRODUCT
import id.itborneo.blanjaa.core.utils.constant.EXTRA_PRODUCT
import id.itborneo.blanjaa.core.utils.constant.EXTRA_USER
import id.itborneo.blanjaa.core.utils.layoutManager.GridAutofitLayoutManager
import id.itborneo.blanjaa.core.utils.mapperUtils.DataMapper
import id.itborneo.blanjaa.core.utils.ui.ScreenUtils
import id.itborneo.blanjaa.home.carousel.ImageSlider
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.partial_appbar.*


class HomeFragment : FragmentWithViewModelandNav() {

    private val TAG = "HomeFragment"

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductAdapter

    private var isGetBestProduct = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAppbar()
        initializeRecyclerView()
        initCarasoul()

        getInitData()
        getData()
//        observerupdatedproduct()

        broadcastReceive()
        getToken()
    }


    private fun getInitData() {
        val intentUser = arguments?.getParcelable<UserModel>(EXTRA_USER)
        Log.d(TAG, "intentUser" + arguments?.getParcelable<UserModel>(EXTRA_USER).toString())
        if (intentUser != null) {
            viewModel.user = intentUser

        }
    }


    private fun getData() {

        loading()

        viewModel.getAllCategory().observe(viewLifecycleOwner) {
            Log.d(TAG, it.data.toString())

            val data = it.data
            if (data != null) {

                categoryAdapter.setCategory(it.data)

            }


            viewModel.refreshProduct()
            viewModel.getAllProduct().observe(viewLifecycleOwner) {
                Log.d(TAG, "getAllProduct" + it.data.toString())

                val data = it.data
                if (!data.isNullOrEmpty()) {


                    viewModel.listProduct = DataMapper.productEntityToModel(data)
                    val reversed = (viewModel.listProduct as MutableList).asReversed()

                    productAdapter.setProducts(reversed)


                    getBustProduct(DataMapper.productEntityToModel(data))

                }

            }
        }


    }


    lateinit var mysms: BroadcastReceiver

    private fun broadcastReceive() {

//        LocalBroadcastManager.getInstance(this)
//            .registerReceiver(mysms, IntentFilter("your_action_name"))

        mysms = object : BroadcastReceiver() {
            override fun onReceive(arg0: Context?, arg1: Intent) {

//                viewModel.updatedproduct.value = true
//                Log.d(TAG, "broadcastReceive onReceive ${viewModel.updatedproduct.value}")

                viewModel.refreshProduct()

            }
        }
        requireContext().registerReceiver(mysms, IntentFilter("your_action_name"))
    }

    private fun getBustProduct(products: List<ProductModel>) {

        val getBestProductObserver = viewModel.getBestProduct()

        getBestProductObserver.observe(viewLifecycleOwner) {
            loading(false)
            Log.d(TAG, "getBestProductObserver" + products)

            //gagal di list  da
            Log.d(TAG, "getBestProductObserver : listProduct" + products)

            val data = it.data
            if (data != null) {


                val bestProduct = mutableListOf<ProductModel>()

                data.forEach { bestProd ->
                    viewModel.listProduct.forEach lit2@{ prod ->
                        if (bestProd.productId == prod.id) {
                            bestProduct.add(prod)
                            return@lit2
                        }
                    }
                }

                if (!isGetBestProduct) {
                    Log.d(TAG, "getBestProductObserver : outut" + bestProduct)

                    updateCarasoul(bestProduct)
                    isGetBestProduct = true
                }

                getBestProductObserver.removeObservers(requireActivity())
            }

        }
    }

    private fun initAppbar() {
        tbApp.title = "Home"

    }


    private lateinit var slider: ImageSlider

    private fun initCarasoul() {
        slider = ImageSlider(
            requireContext(), viewPager, SliderDots
        )
    }

    private fun updateCarasoul(data: List<ProductModel>) {

        slider.initialize(data) { besProduct ->
            navActionProduct(besProduct)

        }
    }

    private fun navActionProduct(product: ProductModel) {

        val bundle = bundleOf(
            EXTRA_PRODUCT to product,
            EXTRA_LIST_PRODUCT to viewModel.listProduct,
            EXTRA_USER to viewModel.user

        )
        navController.navigate(
            R.id.action_homeFragment_to_detailFragment,
            bundle
        )

    }

    private fun initializeRecyclerView() {


        // category
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)



        categoryAdapter = CategoryAdapter { categoryChoosed ->

            val newList = mutableListOf<ProductModel>()

            viewModel.listProduct.forEach {
                if (it.categoryId == categoryChoosed.id) {
                    newList.add(it)
                }
            }

            navAction(newList)
        }

        rvCategory.adapter = categoryAdapter
        rvCategory.layoutManager = layoutManager


        //product rv


        productAdapter = ProductAdapter {
            navActionProduct(it)
        }
        val layoutManager2 =
            GridAutofitLayoutManager(requireContext(), ScreenUtils.getScreenWidth() / 2)
        rvProducts.layoutManager = layoutManager2
        rvProducts.adapter = productAdapter


    }


    private fun navAction(products: List<ProductModel>) {

        val bundle = bundleOf(
            EXTRA_LIST_PRODUCT to products,
            EXTRA_USER to viewModel.user
        )
        navController.navigate(
            R.id.action_homeFragment_to_productByCategoryFragment, bundle
        )

    }

    private fun getToken() {
        @Suppress("DEPRECATION")
        val token = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "getToken $token")
    }


}