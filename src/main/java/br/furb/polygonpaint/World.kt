package br.furb.polygonpaint

import br.furb.polygonpaint.Color.BLACK
import java.awt.event.MouseEvent
import java.util.*

class World {

    private var camera: Camera = Camera(0.toDouble(), 400.toDouble(), 0.toDouble(), 400.toDouble())
    private var selectedGraphicalObject: GraphicalObject
     var backgroundColor: Color = BLACK
    private var graphicalObjects: MutableList<GraphicalObject> = ArrayList()

    init {
        selectedGraphicalObject = GraphicalObject()
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
            obj.draw()
        }

        selectedGraphicalObject.drawBoundingBox()
    }

    fun rezise(width: Int, height: Int) {
        camera.top = camera.down + height ;
        camera.right = camera.left + width;
    }
}
