package id.itborneo.blanjaa.core.ui.theme

import androidx.appcompat.app.AppCompatDelegate
import id.itborneo.blanjaa.core.data.model.ProductModel

object DayNight {

    lateinit var listProduct: List<ProductModel>

    fun disableDayNight() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}