package br.furb.polygonpaint.world.interactions

import br.furb.polygonpaint.GLProvider
import br.furb.polygonpaint.toPoint
import br.furb.polygonpaint.world.Point4D
import br.furb.polygonpaint.world.World
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import javax.media.opengl.GLCanvas

class EditablePointAction(world: World, canvas: GLCanvas) : InteractionBase(world, canvas) {

    private var selectedPoint: Point4D? = null
    private lateinit var lastMousePoint: Point4D

    override fun mousePressed(e: MouseEvent) {
        lastMousePoint = e.toPoint()
        selectedPoint = searchPoint(lastMousePoint)
        println("mousePressed")
    }

    private fun searchPoint(point: Point4D): Point4D? {
        world.selectedGraphicalObject.searchNextPoint(point)
        return world.selectedGraphicalObject.selectedPoint()
    }

    override fun mouseDragged(e: MouseEvent) {
        if (selectedPoint != null) {
            selectedPoint?.sumTo(lastMousePoint.diffPoints(e.toPoint()).inverted())
            world.selectedGraphicalObject.atualizaBBox()
            lastMousePoint = e.toPoint()
            GLProvider.glDrawable.display()
        }
    }

    override fun keyReleased(e: KeyEvent) {
        super.keyReleased(e)
    }

}