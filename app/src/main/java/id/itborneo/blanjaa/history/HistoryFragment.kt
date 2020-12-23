package id.itborneo.blanjaa.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.core.data.model.ProductModel
import id.itborneo.blanjaa.core.ui.parent.FragmentWithViewModelandNav
import id.itborneo.blanjaa.core.utils.mapperUtils.DataMapper
import kotlinx.android.synthetic.main.fragment_history.*


class HistoryFragment : FragmentWithViewModelandNav() {


    private val TAG = "HistoryFragment"

    private lateinit var adapter: HistoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeRecyclerView()
        getData()
    }

    private fun initializeRecyclerView() {
        adapter = HistoryAdapter { wishlist ->
            viewModel.listProduct.forEach { product ->


                if (product.id == wishlist.productId) {
                    navAction(product)
                    return@forEach
                }

            }
        }

        rvHistory.adapter = adapter
        val layoutManager = LinearLayoutManager(requireContext())
        rvHistory.layoutManager = layoutManager
    }

    private fun navAction(product: ProductModel) {

//        val bundle = bundleOf(
//            EXTRA_PRODUCT to product,
//            EXTRA_LIST_PRODUCT to viewModel.listProduct
//        )
//        navController.navigate(
//            R.id.action_explorerFragment_to_detailFragment,
//            bundle
//        )

    }

    private fun getData() {


        viewModel.getAllHistory().observe(viewLifecycleOwner) {
            Log.d(TAG, it.data.toString())
            Log.d(TAG, "user ${viewModel.user.id}")

            val data = it.data
            if (data != null) {

                val myHistory = DataMapper.HistoryResponseToModel(
                    data,
                    viewModel.listProduct,
                    viewModel.user.id
                )

                adapter.setHistories(myHistory)


            }
        }

    }


}