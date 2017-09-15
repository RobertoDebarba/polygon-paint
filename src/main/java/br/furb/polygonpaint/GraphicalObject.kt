package br.furb.polygonpaint

import sun.reflect.generics.reflectiveObjects.NotImplementedException

import javax.media.opengl.GL
import java.util.ArrayList

class GraphicalObject {
    var color: Color
    var graphicalPrimitive: GraphicalPrimitive
    var points: MutableList<Point4D>
    var boundingBox: BoundingBox? = null
    var transformation: Transformacao4D? = null
    fun ehAlter() = points.size > 0 && _ehAlter
    private val _ehAlter: Boolean

    init {
        graphicalPrimitive = GraphicalPrimitive.LINE_LOOP
        color = Color(0f, 0f, 0f)
        points = ArrayList()
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

    fun addPoint(point4D: Point4D) {
        points.add(point4D)
    }
}

