package br.furb.polygonpaint

import br.furb.polygonpaint.Color.WHITE
import sun.reflect.generics.reflectiveObjects.NotImplementedException
import java.awt.event.MouseEvent

import java.util.ArrayList

class GraphicalObject {
    var color: Color = WHITE
    var graphicalPrimitive: GraphicalPrimitive = GraphicalPrimitive.LINE_LOOP
    var points: MutableList<Point4D> = ArrayList()
    lateinit var boundingBox: BoundingBox
    var transformation: Transformacao4D? = null
    var isEditing: Boolean = true
        get() = points.size > 0 && field
        private set

    fun selectPolygon() {
        throw NotImplementedException()
    }

    fun removePolygon() {
        throw NotImplementedException()
    }

    fun drawBoundingBox() {
        if (points.size > 0)
            boundingBox.desenharOpenGLBBox()
    }

    fun draw() {
        gl {
            glColor(WHITE)
            glPointSize(3f)
            glLineWidth(3f)

            lineLoopOrPoint(isLineLoop) {
                points.forEach { glPoint(it) }
            }
        }
    }

    fun selectedPoint() = points.last()

    private fun addPoint(point4D: Point4D) {
        points.add(point4D)
        boundingBox.atualizarBBox(point4D)
    }

    private val MOUSE_RIGHT_BUTON = 1
    private val MOUSE_LEFT_BUTON = 3

    fun mouseClicked(mouse: MouseEvent) {
        if (isRightClick(mouse)) {
            if (!isInitialized())
                initialize(mouse.toPoint())

            addPoint(mouse.toPoint())
        } else if (isInitialized()) {
            points.removeAt(points.size - 1)
            isEditing = false
        }
    }

    private fun initialize(point : Point4D) {
        boundingBox = BoundingBox(point.x, point.y, point.z)
        addPoint(point)
    }

    private fun isInitialized() = points.size > 0

    private fun isRightClick(e: MouseEvent) = e.button == MOUSE_RIGHT_BUTON

    private val isLineLoop = { !(points.size == 2 && points[0] == points[1]) }

}

