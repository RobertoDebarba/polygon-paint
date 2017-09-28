package br.furb.polygonpaint.world.interactions

import br.furb.polygonpaint.GLProvider
import br.furb.polygonpaint.world.World
import br.furb.polygonpaint.world.attributes.GraphicalPrimitive
import java.awt.event.MouseEvent
import javax.media.opengl.GLCanvas

class SwitchPrimitiveAction (world: World, canvas: GLCanvas) : InteractionBase(world, canvas) {

    override fun mousePressed(e: MouseEvent) {
        if(world.selectedGraphicalObject.graphicalPrimitive == GraphicalPrimitive.LINE_LOOP)
            world.selectedGraphicalObject.graphicalPrimitive =  GraphicalPrimitive.LINE_STRIP
        else
            world.selectedGraphicalObject.graphicalPrimitive =  GraphicalPrimitive.LINE_LOOP

        GLProvider.glDrawable.display()
    }

}