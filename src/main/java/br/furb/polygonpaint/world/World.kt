package br.furb.polygonpaint.world

import br.furb.polygonpaint.glu
import br.furb.polygonpaint.world.attributes.Color
import br.furb.polygonpaint.world.attributes.Color.BLACK
import java.util.*


class World {

    private var camera: Camera = Camera(0.toDouble(), 400.toDouble(), 0.toDouble(), 400.toDouble())
    var selectedGraphicalObject: GraphicalObject
    var backgroundColor: Color = BLACK
    private var graphicalObjects: MutableList<GraphicalObject> = ArrayList()

    init {
        selectedGraphicalObject = GraphicalObject()
    }

    fun draw() {
        glu {
            gluOrtho2D(camera.left, camera.right, camera.top, camera.down)
        }
        graphicalObjects.forEach { it.draw() }
        selectedGraphicalObject.drawBoundingBox()
    }

    fun addGraphicalObject( graphicalObject : GraphicalObject){
        graphicalObjects.add(graphicalObject)
        selectedGraphicalObject = graphicalObject
    }
}
