package br.furb.polygonpaint.world

import br.furb.polygonpaint.gl
import br.furb.polygonpaint.glPoint
import br.furb.polygonpaint.glu
import br.furb.polygonpaint.point
import br.furb.polygonpaint.world.attributes.Color
import br.furb.polygonpaint.world.attributes.Color.BLACK
import java.util.*


class World {

    private var camera: Camera = Camera(0.toDouble(), 400.toDouble(), 0.toDouble(), 400.toDouble())
    var selectedGraphicalObject: GraphicalObject
    var backgroundColor: Color = BLACK
    private var internalGraphicalObjects: MutableList<GraphicalObject> = ArrayList()
    val graphicalObjects: List<GraphicalObject>
        get() = this.internalGraphicalObjects.toList()

    /**
     * Sempre inicializa um objeto qualquer para não estar nulo
     */
    init {
        selectedGraphicalObject = GraphicalObject()
    }

    /**
     * Desenha o mundo
     */
    fun draw() {
        glu {
            gluOrtho2D(camera.left, camera.right, camera.top, camera.down)
        }
        internalGraphicalObjects.forEach { it.draw() }
        selectedGraphicalObject.drawBoundingBox()
    }

    /**
     * Adiciona um Objeto grafico no mundo
     */
    fun addGraphicalObject(graphicalObject: GraphicalObject) {
        internalGraphicalObjects.add(graphicalObject)
        selectedGraphicalObject = graphicalObject
    }

    /**
     * Remove um objeto grafico do mundo
     */
    fun deleteGraphicalObject(child: GraphicalObject) {
        if(!internalGraphicalObjects.remove(child)){
            internalGraphicalObjects.forEach { it.removeChild(child) }
        }
    }
}
