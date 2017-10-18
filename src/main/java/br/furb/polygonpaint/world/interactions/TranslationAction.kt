package br.furb.polygonpaint.world.interactions

import br.furb.polygonpaint.GLProvider
import br.furb.polygonpaint.world.World
import java.awt.event.KeyEvent
import javax.media.opengl.GLCanvas

class TranslationAction(world: World, canvas: GLCanvas) : InteractionBase(world, canvas) {

    /**
     * Cada tecla selecionada Verifica se deve transladar o objeto e o faz em 5 posições
     */
    override fun keyPressed(e: KeyEvent) {
        val move = 5.0
        when (e.keyCode) {
            // right
            39 -> translation(move, 0.0, 0.0)
            // left
            37 -> translation(-move, 0.0, 0.0)
            // up
            38 -> translation(0.0, -move, 0.0)
            // down
            40 -> translation(0.0, move, 0.0)
        }

        super.keyPressed(e)
    }

    private fun translation(tx: Double, ty : Double, tz :Double) {
        world.selectedGraphicalObject.translate(tx, ty, tz)
        GLProvider.glDrawable.display()
    }
}