package app.kotleni.arcoredemo

import android.app.Activity
import android.content.Context
import android.opengl.GLSurfaceView
import com.google.ar.core.Session

class MyGLSurfaceView(activity: Activity, session: Session) : GLSurfaceView(activity.applicationContext) {
    private val renderer: MyGLRenderer

    init {
        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2)

        renderer = MyGLRenderer(session, activity)

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(renderer)
    }
}
