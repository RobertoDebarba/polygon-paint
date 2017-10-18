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

    /**
     * Posiciona na Ação precionada
     */
    override fun keyPressed(e: KeyEvent) {
        println(" --- keyPressed ---")
        System.out.println(e.keyCode)

        when (e.keyCode) {
            // 0
            48 -> InsertionPolygonAction(world, canvas)
            // 1
            49 -> EditablePointAction(world, canvas)
            // 2
            50 -> SwitchPrimitiveAction(world, canvas)
            // 3
            51 -> SwitchColorAction(world, canvas)
            // 4
            52 -> SelectPolygonAction(world, canvas)
            // 5
            53 -> AssociateChildAction(world, canvas)
            // T
            84 -> TranslationAction(world, canvas)
            // S
            83 -> ScaleAction(world, canvas)
            // R
            82 -> RotationAction(world, canvas)
        }
    }

    override fun keyReleased(e: KeyEvent) {
    }

    /**
     * Torna esse o novo listner do canvas
     */
    init {
        canvas.mouseListeners.forEach { canvas.removeMouseListener(it) }
        canvas.addMouseListener(this)
        canvas.mouseMotionListeners.forEach { canvas.removeMouseMotionListener(it) }
        canvas.addMouseMotionListener(this)
        canvas.keyListeners.forEach { canvas.removeKeyListener(it) }
        canvas.addKeyListener(this)
    }

}