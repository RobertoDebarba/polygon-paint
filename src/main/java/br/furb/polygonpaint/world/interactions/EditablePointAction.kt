package br.furb.polygonpaint.world.interactions

import br.furb.polygonpaint.GLProvider
import br.furb.polygonpaint.isLeftButton
import br.furb.polygonpaint.toPoint
import br.furb.polygonpaint.world.Point4D
import br.furb.polygonpaint.world.World
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import javax.media.opengl.GLCanvas

class EditablePointAction(world: World, canvas: GLCanvas) : InteractionBase(world, canvas) {

    private var selectedPoint: Point4D? = null
    private lateinit var lastMousePoint: Point4D

    /**
     * Busca o ponto de edição
     */
    override fun mousePressed(e: MouseEvent) {
        if(e.isLeftButton()){
            lastMousePoint = e.toPoint()
            selectedPoint = searchPoint(lastMousePoint)
        }else {
            world.selectedGraphicalObject.removeSelectedPoint()
            GLProvider.glDrawable.display()
        }
    }

    /**
     * Procura pelo ponto mais proximo no poligono selecionado
     */
    private fun searchPoint(point: Point4D): Point4D? {
        world.selectedGraphicalObject.searchNextPoint(point)
        return world.selectedGraphicalObject.selectedPoint()
    }

    /**
     * Move o ponto conforme o arrastar do mouse
     */
    override fun mouseDragged(e: MouseEvent) {
        if (selectedPoint != null) {
            world.selectedGraphicalObject.sumValueToPoint(selectedPoint!!, lastMousePoint.diffPoints(e.toPoint()).inverted())
            lastMousePoint = e.toPoint()
            GLProvider.glDrawable.display()
        }
    }
}