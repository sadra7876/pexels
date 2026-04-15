import android.content.Context
import androidx.work.*
import com.example.core.worker.syncPhotoWorker.PhotosSyncWorker
import java.util.concurrent.TimeUnit

fun scheduleSyncWork(context: Context) {

    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val request = PeriodicWorkRequestBuilder<PhotosSyncWorker>(
        15, TimeUnit.MINUTES
    )
        .setConstraints(constraints)
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "sync_work",
        ExistingPeriodicWorkPolicy.KEEP,
        request
    )
}