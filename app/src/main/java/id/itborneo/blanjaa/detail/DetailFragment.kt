package id.itborneo.blanjaa.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.core.data.model.AddWishModel
import id.itborneo.blanjaa.core.data.model.ProductModel
import id.itborneo.blanjaa.core.data.model.UserModel
import id.itborneo.blanjaa.core.ui.parent.FragmentWithNav
import id.itborneo.blanjaa.core.ui.viewModel.ViewModelFactory
import id.itborneo.blanjaa.core.utils.constant.EXTRA_LIST_PRODUCT
import id.itborneo.blanjaa.core.utils.constant.EXTRA_PRODUCT
import id.itborneo.blanjaa.core.utils.constant.EXTRA_USER
import id.itborneo.blanjaa.core.utils.mapperUtils.DataMapper
import id.itborneo.blanjaa.core.utils.ui.BottomNavigationUtils
import id.itborneo.blanjaa.core.utils.ui.SpinKitUtils
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : FragmentWithNav() {

    private lateinit var product: ProductModel
    private lateinit var list: List<ProductModel>
    private lateinit var viewModel: DetailViewModel
    private var wishlistId = "0"
    private var checkoutId = "0"

    private var isWished = false
    private var isInCeckout = false


    private val TAG = "DetailFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
//        return inflater.inflate(R.layout.frag/ment_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        hideBottomNav()
        buttonListener()
        getInitData()
        getData()
    }

    private fun getData() {


        viewModel.getAllWishlist().observe(viewLifecycleOwner) {
            Log.d(TAG, "getData wishlist" + it.data.toString())

            val data = it.data
            if (data != null) {


                val allWishlist = DataMapper.WishResponseToModel(data, list)

                viewModel.listWishlist = DataMapper.AllWishToUserWish(
                    allWishlist,
                    viewModel.user.id
                )


                viewModel.listWishlist.forEach { wishitem ->
                    if (wishitem.productId == product.id) {

                        isWished = true
                        wishlistId = wishitem.id
                        updateUI()
                        return@forEach
                    }

                }

            }
        }

        viewModel.getAllCheckout().observe(viewLifecycleOwner) {
            Log.d(TAG, "getData getAllCheckout" + it.data.toString())

            val data = it.data
            if (data != null) {


                val allCheckout = DataMapper.WishResponseToModel(data, list)

                viewModel.listCheckout = DataMapper.AllWishToUserWish(
                    allCheckout,
                    viewModel.user.id
                )


                viewModel.listCheckout.forEach { checkout ->
                    if (checkout.productId == product.id) {

                        isInCeckout = true
                        checkoutId = checkout.id
                        updateUI()
                        return@forEach
                    }

                }

            }
        }

        viewModel.refreshCheckout()
        viewModel.refreshWishList()
    }

    private fun buttonListener() {

        btnWishlist.setOnClickListener {

            if (!isWished) {
                addWish(
                    AddWishModel(

                        productId = product.id,
                        userId = viewModel.user.id
                    )
                )
            } else {
                removeWish()
            }
        }

        btnCheckout.setOnClickListener {
            if (!isInCeckout) {
                addCheckout(
                    AddWishModel(

                        productId = product.id,
                        userId = viewModel.user.id
                    )
                )
            } else {
                removeCheckout()
            }
        }
    }

    private fun removeCheckout() {
        loading()


        viewModel.removeCheckoutItem(checkoutId).observe(viewLifecycleOwner) {
            loading(false)

            Log.d(TAG, it.data.toString())


            val data = it.data
            if (data != null) {

//                navAction(product, R.id.action_detailFragment_to_wishlistFragment)

                isInCeckout = false
                updateUI()
            }


        }

    }

    private fun addCheckout(item: AddWishModel) {
        loading()


        viewModel.addCheckoutItem(item).observe(viewLifecycleOwner) {
            loading(false)

            Log.d(TAG, it.data.toString())

            val data = it.data
            if (data != null) {

//                navAction(product, R.id.action_detailFragment_to_wishlistFragment)
                checkoutId = data.data.id
                viewModel.refreshCheckout()
                isInCeckout = true

                updateUI()
            }

        }
    }


    private fun loading(showLoading: Boolean = true) {
        if (showLoading) {
            SpinKitUtils.show(spinKitLoading)
        } else {
            SpinKitUtils.hide(spinKitLoading)
        }
    }

    private fun navAction(product: ProductModel, action: Int) {

//        val bundle = bundleOf(
//            EXTRA_BUKU to buku,
//        )
        navController.navigate(
            action,
//            bundle
        )

    }


    private fun getInitData() {
        val intent = arguments?.getParcelable<ProductModel>(EXTRA_PRODUCT)
        val intent2 = arguments?.getParcelableArrayList<ProductModel>(EXTRA_LIST_PRODUCT)
        val intent3 = arguments?.getParcelable<UserModel>(EXTRA_USER)

        Log.d(TAG, "$intent")
        if (intent != null && intent2 != null)
            product = intent
        if (intent2 != null) {
            list = intent2.toList()
            Log.d(TAG, "$intent2")
        }
        if (intent3 != null) {
            viewModel.user = intent3
        }

        updateUI()

    }

    private fun updateUI() {

        tvName.text = product.name
        tvDescription.text = product.description
        tvPrice.text = "Rp ${product.price}"

        Glide.with(requireContext())
            .load(product.imagePath)
            .into(ivProduct)

        if (isWished) {
            btnWishlist.text = "wished"
        } else {
            btnWishlist.text = " Add to Wish"
        }




        if (isInCeckout) {
            btnCheckout.text = "in Checkout"
        } else {
            btnCheckout.text = " Add to Checkout"
        }

        collapseAppbar()
        toolbarDetail()

    }

    private fun hideBottomNav() {
        BottomNavigationUtils.invisible(activity)
    }

    private fun toolbarDetail() {


        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }


    private fun collapseAppbar() {
        collapsing_toolbar.title = product.name


        //when collapse jadi kecil
        collapsing_toolbar.setCollapsedTitleTextColor(
            ContextCompat.getColor(requireContext(), R.color.white)
        )


        //when expanded jadi gede. jadi dihilangan tulisan dengan transparent
        collapsing_toolbar.setExpandedTitleColor(

            ContextCompat.getColor(requireContext(), android.R.color.transparent)
        )


    }

    private fun initViewModel() {

        val factory = ViewModelFactory.getInstance(requireActivity())

        viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

    }

    private fun addWish(wishItem: AddWishModel) {
        loading()


        viewModel.addWishItem(wishItem).observe(viewLifecycleOwner) {
            loading(false)

            Log.d(TAG, it.data.toString())

            val data = it.data
            if (data != null) {

                wishlistId = data.data.id
                viewModel.refreshWishList()
                isWished = true

                updateUI()
            }

        }


    }

    private fun removeWish() {
        loading()
        viewModel.removeWishItem(wishlistId).observe(viewLifecycleOwner) {
            Log.d(TAG, it.data.toString())
            loading(false)


            val data = it.data
            if (data != null) {

//                navAction(product, R.id.action_detailFragment_to_wishlistFragment)

                isWished = false
                updateUI()
            }


        }
    }

}