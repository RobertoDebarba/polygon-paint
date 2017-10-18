package br.furb.polygonpaint.world.interactions

import br.furb.polygonpaint.GLProvider
import br.furb.polygonpaint.isLeftButton
import br.furb.polygonpaint.toPoint
import br.furb.polygonpaint.world.GraphicalObject
import br.furb.polygonpaint.world.World
import java.awt.event.MouseEvent
import javax.media.opengl.GLCanvas

class InsertionPolygonAction(world: World, canvas: GLCanvas) : InteractionBase(world, canvas) {

    private var graphicalObject: GraphicalObject? = null

    /**
     * Garante que o Objeto já está criado
     * Caso não esteja criar, adicionar no mundo e repitir o ponto.
     */
    private fun ensureGraphicalObject(block: () -> Unit) {
        if (graphicalObject == null) {
            val graphicalObject = GraphicalObject()
            this.graphicalObject = graphicalObject
            world.addGraphicalObject(graphicalObject)
            block()
            graphicalObject.addPoint(graphicalObject.selectedPoint()!!.copy())
        } else
            block()
    }

    /**
     * A cada click para o lado direito do mouse adicionar um ponto para o poligono
     * A cada click para o lado esquerdo termina de dezenhar o poligono
     */
    override fun mouseClicked(e: MouseEvent) {
        if (e.isLeftButton()) {
            ensureGraphicalObject {
                graphicalObject?.addPoint(e.toPoint())
            }
        } else {
            world.selectedGraphicalObject.removeSelectedPoint()
            InsertionPolygonAction(world, canvas)
        }
        GLProvider.glDrawable.display()
    }

    /**
     * Enquanto está adicionado um poligono deve manter o rastro em tela
     */
    override fun mouseMoved(e: MouseEvent) {
        if (graphicalObject != null) {
            graphicalObject?.selectedPoint()?.moveTo(e.toPoint())
            GLProvider.glDrawable.display()
        }
    }
}