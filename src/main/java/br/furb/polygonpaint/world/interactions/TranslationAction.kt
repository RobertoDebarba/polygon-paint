package br.furb.polygonpaint.world.interactions

import br.furb.polygonpaint.GLProvider
import br.furb.polygonpaint.world.World
import java.awt.event.KeyEvent
import javax.media.opengl.GLCanvas

class TranslationAction(world: World, canvas: GLCanvas) : InteractionBase(world, canvas) {

    override fun keyPressed(e: KeyEvent) {

        when (e.keyCode) {
            // right
            39 -> translationRight()
            // left
            37 -> translationLeft()
            // up
            38 -> translationUp()
            // down
            40 -> translationDown()
        }

        super.keyPressed(e)
    }

    private fun translationLeft() {
        world.selectedGraphicalObject.translate(-2.0, 0.0, 0.0)
        GLProvider.glDrawable.display()
    }

    private fun translationRight() {
        world.selectedGraphicalObject.translate(2.0, 0.0, 0.0)
        GLProvider.glDrawable.display()
    }

    private fun translationUp() {
        world.selectedGraphicalObject.translate(0.0, -2.0, 0.0)
        GLProvider.glDrawable.display()
    }

    private fun translationDown() {
        world.selectedGraphicalObject.translate(0.0, 2.0, 0.0)
        GLProvider.glDrawable.display()
    }
}