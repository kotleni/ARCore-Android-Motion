package app.kotleni.arcoredemo

import android.app.Activity
import android.view.View


/** Helper to set up the Android full screen mode.  */
object FullScreenHelper {
    /**
     * Sets the Android fullscreen flags. Expected to be called from [ ][Activity.onWindowFocusChanged].
     *
     * @param activity the Activity on which the full screen mode will be set.
     * @param hasFocus the hasFocus flag passed from the [Activity.onWindowFocusChanged] callback.
     */
    fun setFullScreenOnWindowFocusChanged(activity: Activity, hasFocus: Boolean) {
        if (hasFocus) {
            // https://developer.android.com/training/system-ui/immersive.html#sticky
            activity
                .window
                .decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }
}