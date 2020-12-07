package id.itborneo.blanjaa.core.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import id.itborneo.blanjaa.R
import id.itborneo.blanjaa.app.AppActivity

class HandleMessageService : FirebaseMessagingService() {


    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    private val TAG = "HandleMessage"
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "remoteMessage ${remoteMessage.data}")


            createNotificationChannel()
            val intent = Intent(this, AppActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            broadcastToActivity()
            playSound()
            newProductNotif("Product Baru", "dapatkan produt baru ini", intent)
        }


    }

    private fun broadcastToActivity() {
        val intent = Intent ("your_action_name")
        sendBroadcast(intent)
    }


    private fun newProductNotif(title: String, message: String, intent: Intent) {
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val notif = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentText(message)
            .setContentTitle(title)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        val buildNotif = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        buildNotif.notify(0, notif.build())


    }

    private val CHANNEL_ID = "111"

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun playSound() {
        val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val ringtoneSound = RingtoneManager.getRingtone(applicationContext, sound)
        ringtoneSound.play()
    }


}