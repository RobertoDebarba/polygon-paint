package br.furb.polygonpaint

import br.furb.polygonpaint.world.Point4D
import br.furb.polygonpaint.world.attributes.Color
import br.furb.polygonpaint.world.attributes.GraphicalPrimitive
import java.awt.event.MouseEvent
import javax.media.opengl.GL
import javax.media.opengl.glu.GLU

/**
 * Coloca como contexto o GL
 */
fun gl(block: GL.() -> Unit) = GLProvider.gl.block()

/**
 * Coloca como contexto o GLU
 */
fun glu(block: GLU.() -> Unit) = GLProvider.glu.block()

/**
 * Atribui a cor de parametro
 */
fun glColor(color: Color) = gl { glColor3f(color.red, color.green, color.blue) }

/**
 * Desenha o ponto em x e y
 */
fun glPoint(point: Point4D) = gl { glVertex2d(point.x, point.y) }

/**
 * Preparação para desenhar Com a primitiva LINE_LOOP
 */
fun lineLoop(block: () -> Unit) { run { drawGl(GL.GL_LINE_LOOP, block) } }

/**
 * Preparação para desenhar Com a primitiva LINE_STRIP
 */
fun lineStrip(block: () -> Unit) { run { drawGl(GL.GL_LINE_STRIP, block) } }

/**
 * Preparação para desenhar Com a primitiva POINTS
 */
fun point(block: () -> Unit) { run { drawGl(GL.GL_POINTS, block) } }

/**
 * Faz o encapsulamento de desenho no GL
 */
private fun drawGl(primitive: Int, block: () -> Unit) {
    run {
        gl { glBegin(primitive) }
        block()
        gl { glEnd() }
    }
}

/**
 * Faz o Preparativo para o desenho de acordo com a primitiva
 */
fun polygon(graphicalPrimitive: GraphicalPrimitive, sizePoints: Int, block: () -> Unit) {
    when{
        sizePoints == 1 -> point(block)
        graphicalPrimitive == GraphicalPrimitive.LINE_LOOP -> lineLoop(block)
        graphicalPrimitive == GraphicalPrimitive.LINE_STRIP -> lineStrip(block)
    }
}

/**
 * Retorna a posição do mouse em Ponto4D com os eixos x e y
 */
fun MouseEvent.toPoint() = Point4D(this.x.toDouble(), this.y.toDouble())

/**
 * Retorna se evento refere-se ao click direito do mouse
 */
fun MouseEvent.isLeftButton() = this.button == 1
