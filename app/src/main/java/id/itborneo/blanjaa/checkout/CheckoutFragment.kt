package id.itborneo.blanjaa.checkout

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.core.data.model.ProductModel
import id.itborneo.blanjaa.core.data.model.WishlistModel
import id.itborneo.blanjaa.core.source.remote.response.history.AddHistory
import id.itborneo.blanjaa.core.ui.parent.FragmentWithViewModelandNav
import id.itborneo.blanjaa.core.ui.partial.BottomSheet
import id.itborneo.blanjaa.core.utils.constant.EXTRA_LIST_PRODUCT
import id.itborneo.blanjaa.core.utils.constant.EXTRA_PRODUCT
import id.itborneo.blanjaa.core.utils.constant.EXTRA_USER
import id.itborneo.blanjaa.core.utils.mapperUtils.DataMapper
import kotlinx.android.synthetic.main.fragment_checkout.*
import kotlinx.android.synthetic.main.partial_appbar.*
import java.text.SimpleDateFormat
import java.util.*


class CheckoutFragment : FragmentWithViewModelandNav() {


    private val TAG = "CheckoutFragment"

    private lateinit var adapter: CheckoutAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAppbar()
        initializeRecyclerView()
        getInitData()
        getData()


    }

    private fun initAppbar() {
        tbApp.title = "Checkout"

    }


    private fun getInitData() {
        val intent2 = arguments?.getParcelableArrayList<ProductModel>(EXTRA_LIST_PRODUCT)


        if (intent2 != null) {
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


        viewModel.refreshCheckout()

        viewModel.getAllCheckout().observe(viewLifecycleOwner) {
            Log.d(TAG, it.data.toString())
            loading(false)


            val data = it.data
            if (data != null) {


                val allCheckout = DataMapper.WishResponseToModel(data, viewModel.listProduct)

                val mycheckout = DataMapper.AllWishToUserWish(
                    allCheckout,
                    viewModel.user.id
                )


                adapter.setCheckout(mycheckout)

            } else {

                adapter.setCheckout(listOf())
            }


        }


    }

    private fun initializeRecyclerView() {
        adapter = CheckoutAdapter({ wishlist ->

            viewModel.listProduct.forEach { product ->
                if (product.id == wishlist.productId) {
                    navAction(product)
                    return@forEach
                }

            }
        }, { checkoutItem ->
            //bayar
            bottomSheet(checkoutItem)

        })

        rvCheckout.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext())
        rvCheckout.layoutManager = layoutManager
    }


    private fun navAction(product: ProductModel) {

        val bundle = bundleOf(
            EXTRA_PRODUCT to product,
            EXTRA_LIST_PRODUCT to viewModel.listProduct,
            EXTRA_USER to viewModel.user

        )
        navController.navigate(
            R.id.action_checkoutFragment_to_detailFragment,
            bundle
        )

    }

    private fun bottomSheet(checkoutItem: WishlistModel) {
        val bottomSheet = BottomSheet

        bottomSheet.showBottomSheetDialog(
            this,
            listOf(
                "GoPay", "Google Play", "Credit Card"
            )
        ) {


            bottomSheet.close()

            val c: Date = Calendar.getInstance().getTime()
            println("Current time => $c")

            val df = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val formattedDate: String = df.format(c)


            val addCheckout = AddHistory(
                productId = checkoutItem.productId,
                userId = viewModel.user.id,
                date = formattedDate,
                payment = it

            )
            actionpayNow(checkoutItem, addCheckout)

        }
    }

    fun actionpayNow(checkoutItem: WishlistModel, addHistory: AddHistory) {
        viewModel.removeCheckoutItem(checkoutItem.id).observe(viewLifecycleOwner) {
            Log.d(TAG, it.data.toString())

            val data = it.data
            if (data != null) {


                viewModel.addHistory(addHistory).observe(viewLifecycleOwner) {

                    viewModel.refreshCheckout()

                    Toast.makeText(
                        requireContext(),
                        "Dibayarkan, silahkan periksa history",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }


        }
    }
}