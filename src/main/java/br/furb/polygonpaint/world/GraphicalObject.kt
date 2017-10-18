package br.furb.polygonpaint.world

import br.furb.polygonpaint.*
import br.furb.polygonpaint.world.attributes.Color
import br.furb.polygonpaint.world.attributes.Color.WHITE
import br.furb.polygonpaint.world.attributes.GraphicalPrimitive
import com.sun.xml.internal.ws.addressing.EndpointReferenceUtil.transform
import sun.reflect.generics.reflectiveObjects.NotImplementedException
import java.util.*

class GraphicalObject {
    var color: Color = WHITE
    var graphicalPrimitive: GraphicalPrimitive = GraphicalPrimitive.LINE_LOOP
    private var points: MutableList<Point4D> = ArrayList()
    private lateinit var boundingBox: BoundingBox
    private var transformation: Transformacao4D = Transformacao4D()
    private var selectedPoint: Point4D? = null
    private var children: MutableList<GraphicalObject> = ArrayList()

    /**
     * Se houver BoundingBox Desenha respeitando a matriz de transformação
     */
    fun drawBoundingBox() {
        if (hasBoundingBox())
            useTransformation { boundingBox.desenharOpenGLBBox() }
    }

    private fun hasBoundingBox(): Boolean = !points.isEmpty()

    /**
     * Desenha o Objeto e seus objetos filhos no grafo de cena
     */
    fun draw() {
        gl {
            glColor(color)
            glPointSize(3f)
            glLineWidth(3f)

            useTransformation {
                polygon(graphicalPrimitive, points.size) {
                    points.forEach { glPoint(it) }
                }

                children.forEach { it.draw() }
            }
        }
    }

    private fun useTransformation(block: () -> Unit) {
        gl {
            glPushMatrix()
            glMultMatrixd(transformation.GetDate(), 0)
        }
        block()
        gl {
            glPopMatrix()
        }

    }

    /**
     * Retorna o Ponto selecionado
     */
    fun selectedPoint(): Point4D? {
        return when {
            selectedPoint != null -> selectedPoint
            hasBoundingBox() -> points.last()
            else -> null
        }
    }

    /**
     * Adiciona um ponto na lista de pontos respeitando as alterações necessarias na BoundingBox
     */
    fun addPoint(point4D: Point4D) {
        if (!hasBoundingBox())
            boundingBox = BoundingBox(point4D)
        else
            boundingBox.atualizarBBox(point4D)

        points.add(point4D)
    }

    /**
     * Remove o Ponto Selecionado
     */
    fun removeSelectedPoint() {
        points.remove(selectedPoint())
        refreshBBox()
    }

    /**
     * Atribui como ponto selecionado o ponto mais proximo do ponto de parametro
     */
    fun searchNextPoint(point: Point4D) {
        selectedPoint = points.minBy { distancePoints(it, point) }
    }

    private fun distancePoints(pt1: Point4D, pt2: Point4D): Double {
        return Math.pow(pt1.x - pt2.x, 2.0) + Math.pow(pt1.y - pt2.y, 2.0)
    }

    private fun refreshBBox() {
        if (!points.isEmpty()) {
            boundingBox = BoundingBox(points.first())
            points.forEach { boundingBox.atualizarBBox(it) }
        }
    }

    /**
     * Função retorna dizendo se o ponto de parametro está dento do Objeto
     */
    fun isInternal(point: Point4D): Boolean {
        if (boundingBox.isInternal(point)) {
            var intersections = 0
            for (i in 1 until points.size) {
                val pointOne = points[i - 1]
                val pointTwo = points[i]

                if (pointIntersect(point, pointOne, pointTwo))
                    intersections++
            }
            val pointOne = points[0]
            val pointTwo = points[points.size - 1]

            if (pointIntersect(point, pointOne, pointTwo))
                intersections++

            if (intersections % 2 != 0)
                return true
        }

        return false
    }

    private fun pointIntersect(point: Point4D, pointOne: Point4D, pointTwo: Point4D): Boolean {
        val yOneMenor = pointOne.y < pointTwo.y
        val menorY = if (yOneMenor) pointOne.y else pointTwo.y
        val maiorY = if (!yOneMenor) pointOne.y else pointTwo.y
        if (point.y in menorY..maiorY) {
            if (point.x <= pointOne.x || point.x <= pointTwo.x) {
                if (point.x <= pointOne.x && point.x <= pointTwo.x) {
                    return true
                }

                // Parece que só precisa dos cálculos pesados se o ponto clicado estiver
                //   dentro da boundBox Criado pelos dois pontos
                //ScanLine
                val ti = (point.y - pointOne.y) / (pointTwo.y - pointOne.y)
                val xi = pointOne.x + (pointTwo.x - pointOne.x) * ti

                return point.x <= xi
            }
        }
        return false

    }

    /**
     * Atribui translação passada como parametro para a matriz de transformações
     */
    fun translate(tx: Double, ty: Double, tz: Double) {
        val matrizTranslate = Transformacao4D()
        matrizTranslate.atribuirTranslacao(tx, ty, tz)
        transformation = matrizTranslate.transformMatrix(transformation)
    }

    /**
     * Atribui escala passada como parametro para a matriz de transformações
     */
    fun scale(proportion : Double){
        transformWithCenterBBox {
            val matrizTmpEscala = Transformacao4D()
            matrizTmpEscala.atribuirEscala(proportion, proportion, 1.0)
            return@transformWithCenterBBox matrizTmpEscala
        }
    }

    /**
     * Atribui rotação passada como parametro para a matriz de transformações
     */
    fun rotation(radians : Double){
        transformWithCenterBBox {
            val matrizTmpEscala = Transformacao4D()
            matrizTmpEscala.atribuirRotacaoZ(radians)
            return@transformWithCenterBBox matrizTmpEscala
        }
    }

    private fun transformWithCenterBBox(block: () -> Transformacao4D) {
        var matrizTmp = Transformacao4D()

        boundingBox.processarCentroBBox()
        var pontoApoio = transformation.transformPoint(boundingBox.centro.inverted())

        val matrizTmpTranslacao = Transformacao4D()
        matrizTmpTranslacao.atribuirTranslacao(pontoApoio.x, pontoApoio.y, pontoApoio.z)
        matrizTmp = matrizTmpTranslacao.transformMatrix(matrizTmp)

        matrizTmp = block().transformMatrix(matrizTmp)

        pontoApoio = pontoApoio.inverted()
        val matrizTmpTranslacaoInversa = Transformacao4D()
        matrizTmpTranslacaoInversa.atribuirTranslacao(pontoApoio.x, pontoApoio.y, pontoApoio.z)
        matrizTmp = matrizTmpTranslacaoInversa.transformMatrix(matrizTmp)

        transformation = matrizTmp.transformMatrix(transformation)
    }

    /**
     * Adiciona o Objeto de parametro como filho deste objeto
     */
    fun addChild(child: GraphicalObject) {
        children.add(child)
    }

    /**
     * Remove o Objeto de parametro da lista de filhos deste objeto
     */
    fun removeChild(child: GraphicalObject) {
        if(!children.remove(child)){
            children.forEach { it.removeChild(child) }
        }
    }

    fun sumValueToPoint(selectedPoint: Point4D, diffSum: Point4D) {
        val point = points.firstOrNull { selectedPoint == it }
        if(point != null){
            point.sumTo(diffSum)
            refreshBBox()
        }
    }
}


