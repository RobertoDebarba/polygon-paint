package br.furb.polygonpaint

import br.furb.polygonpaint.world.Point4D
import br.furb.polygonpaint.world.attributes.Color
import br.furb.polygonpaint.world.attributes.GraphicalPrimitive
import java.awt.event.MouseEvent
import javax.media.opengl.GL
import javax.media.opengl.glu.GLU

fun gl(block: GL.() -> Unit) = GLProvider.gl.block()

fun glu(block: GLU.() -> Unit) = GLProvider.glu.block()

fun glColor(color: Color) = gl { glColor3f(color.red, color.green, color.blue) }

fun glPoint(point: Point4D) = gl { glVertex2d(point.x, point.y) }

fun lineLoop(block: () -> Unit) { run { drawGl(GL.GL_LINE_LOOP, block) } }

fun lineStrip(block: () -> Unit) { run { drawGl(GL.GL_LINE_STRIP, block) } }

fun point(block: () -> Unit) { run { drawGl(GL.GL_POINTS, block) } }

private fun drawGl(primitive: Int, block: () -> Unit) {
    run {
        gl { glBegin(primitive) }
        block()
        gl { glEnd() }
    }
}

fun polygon(graphicalPrimitive: GraphicalPrimitive, sizePoints: Int, block: () -> Unit) {
    when{
        sizePoints == 1 -> point(block)
        graphicalPrimitive == GraphicalPrimitive.LINE_LOOP -> lineLoop(block)
        graphicalPrimitive == GraphicalPrimitive.LINE_STRIP -> lineStrip(block)
    }
}

fun MouseEvent.toPoint() = Point4D(this.x.toDouble(), this.y.toDouble())
fun MouseEvent.isLeftButton() = this.button == 1
