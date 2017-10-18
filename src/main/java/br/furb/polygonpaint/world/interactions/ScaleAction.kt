package br.furb.polygonpaint.world.interactions

import br.furb.polygonpaint.GLProvider
import br.furb.polygonpaint.world.World
import java.awt.event.KeyEvent
import javax.media.opengl.GLCanvas

class ScaleAction(world: World, canvas: GLCanvas) : InteractionBase(world, canvas) {

    /**
     * Atribui a escala para a proporção de dobro ou metade
     */
    override fun keyPressed(e: KeyEvent) {

        when (e.keyCode) {
        // right
            39 -> scale(2.0)
        // left
            37 -> scale(0.5)
        }

        super.keyPressed(e)
    }

    private fun scale(proportion: Double) {
        world.selectedGraphicalObject.scale(proportion)
        GLProvider.glDrawable.display()
    }
}