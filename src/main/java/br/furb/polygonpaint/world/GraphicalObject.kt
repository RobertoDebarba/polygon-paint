package br.furb.polygonpaint.world

import br.furb.polygonpaint.gl
import br.furb.polygonpaint.glColor
import br.furb.polygonpaint.glPoint
import br.furb.polygonpaint.polygon
import br.furb.polygonpaint.world.attributes.Color
import br.furb.polygonpaint.world.attributes.Color.WHITE
import br.furb.polygonpaint.world.attributes.GraphicalPrimitive
import sun.reflect.generics.reflectiveObjects.NotImplementedException
import java.util.*

class GraphicalObject {
    private var color: Color = WHITE
    var graphicalPrimitive: GraphicalPrimitive = GraphicalPrimitive.LINE_LOOP
    private var points: MutableList<Point4D> = ArrayList()
    private lateinit var boundingBox: BoundingBox
    private var transformation: Transformacao4D? = null
    private var selectedPoint: Point4D? = null

    fun selectPolygon() {
        throw NotImplementedException()
    }

    fun removePolygon() {
        throw NotImplementedException()
    }

    fun drawBoundingBox() {
        if (hasBoundingBox())
            boundingBox.desenharOpenGLBBox()
    }

    private fun hasBoundingBox(): Boolean = !points.isEmpty()

    fun draw() {
        gl {
            glColor(color)
            glPointSize(3f)
            glLineWidth(3f)

            polygon(graphicalPrimitive, points.size) {
                points.forEach { glPoint(it) }
            }
        }
    }

    fun selectedPoint(): Point4D? {
        return when {
            selectedPoint != null -> selectedPoint
            hasBoundingBox() -> points.last()
            else -> null
        }
    }

    fun addPoint(point4D: Point4D) {
        if (!hasBoundingBox())
            boundingBox = BoundingBox(point4D)
        else
            boundingBox.atualizarBBox(point4D)

        points.add(point4D)
    }

    private val isLineLoop = { !(points.size == 2 && points[0] == points[1]) }
    fun removeSelectedPoint() {
        points.remove(selectedPoint())
    }

    fun searchNextPoint(point: Point4D) {
        selectedPoint = points.minBy { distancePoints(it, point) }
    }

    private fun distancePoints(pt1: Point4D, pt2: Point4D): Double {
        return Math.pow(pt1.x - pt2.x, 2.0) + Math.pow(pt1.y - pt2.y, 2.0)
    }

    fun atualizaBBox() {
        if(!points.isEmpty()){
            boundingBox = BoundingBox(points.first())
            points.forEach { boundingBox.atualizarBBox(it) }
        }
    }
}

