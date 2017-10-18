package br.furb.polygonpaint.world.interactions

import br.furb.polygonpaint.GLProvider
import br.furb.polygonpaint.toPoint
import br.furb.polygonpaint.world.World
import java.awt.event.MouseEvent
import javax.media.opengl.GLCanvas


class SelectPolygonAction(world: World, canvas: GLCanvas) : InteractionBase(world, canvas) {

    /**
     * Percorre a lista de objetos, o primeiro que achar se torna o objeto selecionado
     */
    override fun mousePressed(e: MouseEvent) {
        val graphicalObject = world.graphicalObjects.firstOrNull {
            it.isInternal(e.toPoint())
        }

        if(graphicalObject != null)
            world.selectedGraphicalObject = graphicalObject

        GLProvider.glDrawable.display()
    }

}