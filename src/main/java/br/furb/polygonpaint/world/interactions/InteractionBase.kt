package br.furb.polygonpaint.world.interactions

import br.furb.polygonpaint.world.World
import java.awt.event.*
import javax.media.opengl.GLCanvas

open class InteractionBase(protected val world: World,
                           protected val canvas: GLCanvas) : MouseListener, MouseMotionListener, ComponentListener, KeyListener {

    override fun mouseReleased(e: MouseEvent) {
    }

    override fun mouseEntered(e: MouseEvent) {
    }

    override fun mouseClicked(e: MouseEvent) {
    }

    override fun mouseExited(e: MouseEvent) {
    }

    override fun mousePressed(e: MouseEvent) {
    }

    override fun mouseMoved(e: MouseEvent) {
    }

    override fun mouseDragged(e: MouseEvent) {
    }

    override fun componentMoved(e: ComponentEvent) {
    }

    override fun componentResized(e: ComponentEvent) {
    }

    override fun componentHidden(e: ComponentEvent) {
    }

    override fun componentShown(e: ComponentEvent) {
    }

    override fun keyTyped(e: KeyEvent) {
    }

    override fun keyPressed(e: KeyEvent) {
        println(" --- keyPressed ---")
        System.out.println(e.keyCode)

        when (e.keyCode) {
        // 0
            48 -> InsertionPolygonAction(world, canvas)
            49 -> EditablePointAction(world, canvas)
        }
    }

    override fun keyReleased(e: KeyEvent) {
    }

    init {
        canvas.mouseListeners.forEach { canvas.removeMouseListener(it) }
        canvas.addMouseListener(this)
        canvas.mouseMotionListeners.forEach { canvas.removeMouseMotionListener(it) }
        canvas.addMouseMotionListener(this)
        canvas.keyListeners.forEach { canvas.removeKeyListener(it) }
        canvas.addKeyListener(this)
    }

}