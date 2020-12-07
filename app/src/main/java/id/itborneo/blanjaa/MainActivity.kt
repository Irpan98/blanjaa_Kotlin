package id.itborneo.blanjaa

import android.content.BroadcastReceiver
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.google.firebase.iid.FirebaseInstanceId
import id.itborneo.blanjaa.app.AppViewModel
import id.itborneo.blanjaa.core.ui.theme.DayNight


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DayNight.disableDayNight()


        getToken()

    }



    private fun getToken() {
        val getToken = FirebaseInstanceId.getInstance().token
        Log.d(TAG, "getToken $getToken")
    }

    override fun onDestroy() {

        super.onDestroy()
    }


}

