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

class World : GLEventListener, MouseListener, MouseMotionListener {

    var camera: Camera
    var selectedGraphicalObject: GraphicalObject
    var backgroundColor: Color
    var graphicalObjects: MutableList<GraphicalObject>

    init {
        backgroundColor = Color(1f, 1f, 1f)
        camera = Camera(0.toDouble(), 400.toDouble(), 0.toDouble(), 400.toDouble())
        graphicalObjects = ArrayList()
        selectedGraphicalObject = GraphicalObject()
    }

    override fun init(glAutoDrawable: GLAutoDrawable) {
        GLProvider.glDrawable = glAutoDrawable
        GLProvider.gl = glAutoDrawable.gl
        GLProvider.glu = GLU()
        GLProvider.glDrawable.gl = DebugGL(GLProvider.gl)
        GLProvider.gl.glClearColor(backgroundColor.red, backgroundColor.green, backgroundColor.blue, 1.0f)
    }

    override fun display(glAutoDrawable: GLAutoDrawable) {
        GLProvider.gl.glClear(GL.GL_COLOR_BUFFER_BIT)

        GLProvider.gl.glMatrixMode(GL.GL_MODELVIEW)
        GLProvider.gl.glLoadIdentity()
        //glu.gluOrtho2D(-400.0f, 400.0f, -400.0f, 400.0f);
        GLProvider.glu.gluOrtho2D(camera.left, camera.right, camera.top, camera.down)

        // seu desenho ...
        for (obj in graphicalObjects) {
            obj.draw(GLProvider.gl)
        }

        GLProvider.gl.glFlush()
    }

    override fun reshape(glAutoDrawable: GLAutoDrawable, i: Int, i1: Int, width: Int, height: Int) {
        println(" --- reshape ---")
        GLProvider.gl.glMatrixMode(GL.GL_PROJECTION)
        GLProvider.gl.glLoadIdentity()
        GLProvider.gl.glViewport(0, 0, width, height)
    }

    override fun displayChanged(glAutoDrawable: GLAutoDrawable, b: Boolean, b1: Boolean) {

    }

    private val MOUSE_RIGHT_BUTON = 1
    private val MOUSE_LEFT_BUTON = 3

    override fun mouseClicked(e: MouseEvent) {
        if (selectedGraphicalObject.ehAlter()) {
            selectedGraphicalObject.addPoint(Point4D(e.x.toDouble(), e.y.toDouble(), 0.0, 1.0))
        } else {
            if (e.button == MOUSE_RIGHT_BUTON) {
                val graphObject = GraphicalObject()
                graphicalObjects.add(graphObject)
                selectedGraphicalObject = graphObject
                graphObject.addPoint(Point4D(e.x.toDouble(), e.y.toDouble(), 0.0, 1.0))
                graphObject.addPoint(Point4D(e.x.toDouble(), e.y.toDouble(), 0.0, 1.0))
            }
        }
        GLProvider.glDrawable.display()
    }

    override fun mousePressed(e: MouseEvent) {

    }

    override fun mouseReleased(e: MouseEvent) {

    }

    override fun mouseEntered(e: MouseEvent) {

    }

    override fun mouseExited(e: MouseEvent) {

    }

    override fun mouseDragged(e: MouseEvent) {

    }

    override fun mouseMoved(e: MouseEvent) {
        if (selectedGraphicalObject.ehAlter()) {
            selectedGraphicalObject.selectedPoint().x = e.x.toDouble()
            selectedGraphicalObject.selectedPoint().y = e.y.toDouble()
            GLProvider.glDrawable.display()
        }
    }
}
