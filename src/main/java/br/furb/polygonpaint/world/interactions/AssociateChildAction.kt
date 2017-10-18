package br.furb.polygonpaint.world.interactions

import br.furb.polygonpaint.GLProvider
import br.furb.polygonpaint.toPoint
import br.furb.polygonpaint.world.World
import java.awt.event.MouseEvent
import javax.media.opengl.GLCanvas

class AssociateChildAction(world: World, canvas: GLCanvas) : InteractionBase(world, canvas) {

    /**
     * Caso ache algum poligono no local selecionado adiciona o poligono selecionado anteriormente
     * como filho do novo poligono selecionado
     */
    override fun mousePressed(e: MouseEvent) {
        val graphicalObject = world.graphicalObjects.firstOrNull {
            it.isInternal(e.toPoint())
        }

        if(graphicalObject != null)
        {
            val child = world.selectedGraphicalObject
            graphicalObject.addChild(child)
            world.deleteGraphicalObject(child)
            world.selectedGraphicalObject = graphicalObject
        }

        GLProvider.glDrawable.display()
    }

}