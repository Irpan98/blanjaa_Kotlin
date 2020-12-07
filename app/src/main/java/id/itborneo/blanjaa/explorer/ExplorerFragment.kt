package id.itborneo.blanjaa.explorer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.core.data.model.ProductModel
import id.itborneo.blanjaa.core.ui.adapter.ProductAdapter
import id.itborneo.blanjaa.core.ui.parent.FragmentWithViewModelandNav
import id.itborneo.blanjaa.core.utils.constant.EXTRA_LIST_PRODUCT
import id.itborneo.blanjaa.core.utils.constant.EXTRA_PRODUCT
import id.itborneo.blanjaa.core.utils.constant.EXTRA_USER
import id.itborneo.blanjaa.core.utils.layoutManager.GridAutofitLayoutManager
import id.itborneo.blanjaa.core.utils.ui.ScreenUtils.getScreenWidth
import kotlinx.android.synthetic.main.fragment_explorer.*
import kotlinx.android.synthetic.main.partial_searchbar.*


class ExplorerFragment : FragmentWithViewModelandNav() {

    private val TAG = "ExplorerFragment"

    private lateinit var adapter: ProductAdapter

    private var querySearch = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explorer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeRecyclerView()
        getData()
        initializeSearchBar()
    }


    private fun initializeRecyclerView() {
        adapter = ProductAdapter {
            navAction(it)
        }

        rvProducts.adapter = adapter
        val layoutManager = GridAutofitLayoutManager(requireContext(), getScreenWidth() / 2)
        rvProducts.layoutManager = layoutManager
    }


    private fun initializeSearchBar() {
        sbPartial.setOnClickListener {
            sbPartial.onActionViewExpanded()
        }

        sbPartial.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d(TAG, "setOnQueryTextListener $query")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(TAG, "onQueryTextChange $newText")

                if (newText != null) {
                    querySearch = newText
                    val newList = viewModel.listProduct.filter {
                        it.name.contains(newText, true)


                    }

                    adapter.setProducts(newList)
                } else {
                    querySearch = ""

                }


                return true
            }

        })


    }

    private fun getData() {
        adapter.setProducts(viewModel.listProduct)


//        viewModel.getAllProduct().observe(viewLifecycleOwner) {
//            Log.d(TAG, it.data.toString())
//
//            val data = it.data
//            if (data != null) {
//
//                viewModel.listProduct = DataMapper.productResponseToModel(data)
//
//                adapter.setProducts(viewModel.listProduct)
//
//            }
//
//
//        }

    }

    private fun navAction(product: ProductModel) {

        val bundle = bundleOf(
            EXTRA_PRODUCT to product,
            EXTRA_LIST_PRODUCT to viewModel.listProduct,
            EXTRA_USER to viewModel.user
        )
        navController.navigate(
            R.id.action_explorerFragment_to_detailFragment,
            bundle
        )

    }


}