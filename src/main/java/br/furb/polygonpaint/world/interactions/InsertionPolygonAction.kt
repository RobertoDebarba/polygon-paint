package br.furb.polygonpaint.world.interactions

import br.furb.polygonpaint.GLProvider
import br.furb.polygonpaint.isLeftButton
import br.furb.polygonpaint.toPoint
import br.furb.polygonpaint.world.GraphicalObject
import br.furb.polygonpaint.world.World
import java.awt.event.MouseEvent
import javax.media.opengl.GLCanvas

class InsertionPolygonAction(world: World, canvas: GLCanvas) : InteractionBase(world, canvas) {

    private var _graphicalObject: GraphicalObject? = null

    private fun ensureGraphicalObject(block: () -> Unit) {
        if (_graphicalObject == null) {
            val graphicalObject = GraphicalObject()
            _graphicalObject = graphicalObject
            world.addGraphicalObject(graphicalObject)
            block()
            graphicalObject.addPoint(graphicalObject.selectedPoint()!!.copy())
        } else
            block()
    }

    override fun mouseClicked(e: MouseEvent) {
        if (e.isLeftButton()) {
            ensureGraphicalObject {
                _graphicalObject?.addPoint(e.toPoint())
            }
        } else {
            world.selectedGraphicalObject.removeSelectedPoint()
            InsertionPolygonAction(world, canvas)
        }
        GLProvider.glDrawable.display()
    }

    override fun mouseMoved(e: MouseEvent) {
        if (_graphicalObject != null) {
            _graphicalObject?.selectedPoint()?.moveTo(e.toPoint())
            GLProvider.glDrawable.display()
        }
    }
}