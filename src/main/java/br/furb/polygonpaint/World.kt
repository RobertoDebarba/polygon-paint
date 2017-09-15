package br.furb.polygonpaint

import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener
import java.util.*
import javax.media.opengl.DebugGL
import javax.media.opengl.GL
import javax.media.opengl.GLAutoDrawable
import javax.media.opengl.GLEventListener
import javax.media.opengl.glu.GLU

class World {

    private var camera: Camera = Camera(0.toDouble(), 400.toDouble(), 0.toDouble(), 400.toDouble())
    private var selectedGraphicalObject: GraphicalObject
    private var backgroundColor: Color = Color(1f, 1f, 1f)
    private var graphicalObjects: MutableList<GraphicalObject> = ArrayList()

    init {
        selectedGraphicalObject = GraphicalObject()
        GLProvider.gl.glClearColor(backgroundColor.red, backgroundColor.green, backgroundColor.blue, 1.0f)
    }

    fun mouseClicked(e: MouseEvent) {
        if(!selectedGraphicalObject.ehAlter()){
            val graphObject = GraphicalObject()
            graphicalObjects.add(graphObject)
            selectedGraphicalObject = graphObject
        }
        selectedGraphicalObject.mouseClicked(e)
    }

    fun mouseMoved(e: MouseEvent): Boolean {
        if (selectedGraphicalObject.ehAlter()) {
            selectedGraphicalObject.selectedPoint().x = e.x.toDouble()
            selectedGraphicalObject.selectedPoint().y = e.y.toDouble()
            return true
        }

        return false
    }

    fun draw() {
        GLProvider.glu.gluOrtho2D(camera.left, camera.right, camera.top, camera.down)

        // seu desenho ...
        for (obj in graphicalObjects) {
            obj.draw(GLProvider.gl)
        }
    }
}
