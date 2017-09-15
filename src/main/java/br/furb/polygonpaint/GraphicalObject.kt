package br.furb.polygonpaint

import sun.reflect.generics.reflectiveObjects.NotImplementedException
import java.awt.event.MouseEvent

import javax.media.opengl.GL
import java.util.ArrayList

class GraphicalObject {
    var color: Color = Color(0f, 0f, 0f)
    var graphicalPrimitive: GraphicalPrimitive = GraphicalPrimitive.LINE_LOOP
    var points: MutableList<Point4D> = ArrayList()
    var boundingBox: BoundingBox? = null
    var transformation: Transformacao4D? = null
    fun ehAlter() = points.size > 0 && _ehAlter
    private var _ehAlter: Boolean

    init {
        _ehAlter = true
    }

    fun selectPolygon() {
        throw NotImplementedException()
    }

    fun removePolygon() {
        throw NotImplementedException()
    }

    fun draw(gl: GL) {
        gl.glColor3f(0f, 0f, 0f)
        gl.glPointSize(3.0f)
        gl.glLineWidth(3.0f)
        gl.glBegin(primitive)
        for (pt in points) {
            gl.glVertex2d(pt.x, pt.y)
        }
        gl.glEnd()
    }

    private val primitive: Int
        get() = when {
            points.size == 2 && points[0] == points[1] -> GL.GL_POINTS
            else -> graphicalPrimitive.id
        }

    fun selectedPoint() = points.last()

    private fun addPoint(point4D: Point4D) {
        points.add(point4D)
    }

    private val MOUSE_RIGHT_BUTON = 1
    private val MOUSE_LEFT_BUTON = 3

    fun mouseClicked(e: MouseEvent) {
        if(e.button == MOUSE_RIGHT_BUTON){
            if(points.size == 0)
                addPoint(Point4D(e.x.toDouble(), e.y.toDouble(), 0.0, 1.0))
            addPoint(Point4D(e.x.toDouble(), e.y.toDouble(), 0.0, 1.0))
        }else if(points.size > 0){
            points.removeAt(points.size -1)
            _ehAlter = false
        }
    }
}

