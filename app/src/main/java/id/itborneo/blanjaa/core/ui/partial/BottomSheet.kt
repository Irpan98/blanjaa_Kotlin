package id.itborneo.blanjaa.core.ui.partial

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import id.itborneo.blanjaa.R
import kotlinx.android.synthetic.main.bottom_sheet_layout.view.*

object BottomSheet {

    private lateinit var bottomSheetDialog: BottomSheetDialog
    fun showBottomSheetDialog(
        fragment: Fragment,
        data: List<String>,
        listener: ((String) -> Unit)
    ) {
        val context = fragment.requireContext()
        val view = fragment.layoutInflater.inflate(R.layout.bottom_sheet_layout, null)
        bottomSheetDialog = BottomSheetDialog(context)
        bottomSheetDialog.setContentView(view)
        view.tvTokopedia.setOnClickListener {
            val marketplace = data[0]
            Toast.makeText(context, marketplace, Toast.LENGTH_SHORT).show()
            listener(marketplace)
        }
        view.tvShopee.setOnClickListener {
            val marketplace = data[1]

            Toast.makeText(context, marketplace, Toast.LENGTH_SHORT).show()
            listener(marketplace)

        }
        view.tvBukalapak.setOnClickListener {
            val marketplace = data[2]
            Toast.makeText(context, marketplace, Toast.LENGTH_SHORT).show()
            listener(marketplace)

        }

        bottomSheetDialog.show()
    }

    fun close() {
        bottomSheetDialog.dismiss()
    }
}