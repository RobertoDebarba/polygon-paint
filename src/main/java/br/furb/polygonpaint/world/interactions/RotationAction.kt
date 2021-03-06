package br.furb.polygonpaint.world.interactions

import br.furb.polygonpaint.GLProvider
import br.furb.polygonpaint.world.World
import java.awt.event.KeyEvent
import javax.media.opengl.GLCanvas

class RotationAction(world: World, canvas: GLCanvas) : InteractionBase(world, canvas) {

    /**
     * Atribui rotação no eixo Z com meio radiano
     */
    override fun keyPressed(e: KeyEvent) {

        when (e.keyCode) {
        // right
            39 -> rotation(.5)
        // left
            37 -> rotation(-.5)
        }

        super.keyPressed(e)
    }

    private fun rotation(radiuns: Double) {
        world.selectedGraphicalObject.rotation(radiuns)
        GLProvider.glDrawable.display()
    }
}