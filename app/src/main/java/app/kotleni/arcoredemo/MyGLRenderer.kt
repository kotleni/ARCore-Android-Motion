package app.kotleni.arcoredemo

import android.app.Activity
import android.content.Context
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.util.Log
import android.view.WindowManager
import com.google.ar.core.RecordingConfig
import com.google.ar.core.Session
import com.google.ar.core.TrackingState
import com.google.ar.core.exceptions.CameraNotAvailableException
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class MyGLRenderer(val session: Session, val activity: Activity) : GLSurfaceView.Renderer {
    private lateinit var cameraColorTexture: Texture

    override fun onSurfaceCreated(unused: GL10, config: EGLConfig) {
        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.4f, 1.0f)

        cameraColorTexture =
            Texture(
                Texture.Target.TEXTURE_EXTERNAL_OES,
                Texture.WrapMode.CLAMP_TO_EDGE,  /*useMipmaps=*/
                false
            )

        session.setCameraTextureNames(intArrayOf(cameraColorTexture.getTextureId()))
    }

    override fun onDrawFrame(unused: GL10) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        val frame =
            try {
                session.update()
            } catch (e: CameraNotAvailableException) {
                Log.e("MyGLRenderer", "Camera not available during onDrawFrame", e)
                // showError("Camera not available. Try restarting the app.")
                return
            }

        val camera = frame.camera

        if (camera.trackingState == TrackingState.PAUSED) {
            println("trackingState is PAUSED")
            return
        }

        val translation = camera.pose.translation
        println("translation is ${translation[0]}, ${translation[1]}, ${translation[2]}, ")
    }

    override fun onSurfaceChanged(unused: GL10, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)
        val windowManager = activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        session.setDisplayGeometry(display.rotation, width, height)
    }
}
