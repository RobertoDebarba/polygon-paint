package br.furb.polygonpaint.world.interactions

import br.furb.polygonpaint.GLProvider
import br.furb.polygonpaint.world.World
import br.furb.polygonpaint.world.attributes.Color
import java.awt.event.MouseEvent
import javax.media.opengl.GLCanvas

class SwitchColorAction(world: World, canvas: GLCanvas) : InteractionBase(world, canvas) {

    /**
     * A cada click alterna entre as cores Vermelho, Branco e Preto
     */
    override fun mousePressed(e: MouseEvent) {
        when (world.selectedGraphicalObject.color) {
            Color.WHITE -> world.selectedGraphicalObject.color = Color.BLACK
            Color.BLACK -> world.selectedGraphicalObject.color = Color.RED
            Color.RED -> world.selectedGraphicalObject.color = Color.WHITE
        }

        GLProvider.glDrawable.display()
    }

}