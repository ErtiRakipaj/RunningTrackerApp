package co.erti.runningtrackerapp.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "running_table")
data class Run (
    var img: Bitmap? = null,
    var timestamp : Long = 0L, //koha fillim run
    var avgSpeedInKMH : Float = 0f,
    var distanceInMetres: Int = 0,
    var timeInMillis: Long = 0L, //kohezgjatje run
    var caloriesBurned: Int = 0
) {
    @PrimaryKey(autoGenerate = true) //room generates the primary key
    var id: Int? = null
}