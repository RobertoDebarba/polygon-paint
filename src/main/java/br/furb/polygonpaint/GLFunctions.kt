package br.furb.polygonpaint

import br.furb.polygonpaint.world.attributes.Color
import br.furb.polygonpaint.world.Point4D
import java.awt.event.MouseEvent
import javax.media.opengl.GL

import javax.media.opengl.glu.GLU

fun gl(block: GL.() -> Unit) = GLProvider.gl.block()

fun glu(block: GLU.() -> Unit) = GLProvider.glu.block()

fun glColor(color: Color) = gl { glColor3f(color.red, color.green, color.blue) }

fun glPoint(point: Point4D) = gl { glVertex2d(point.x, point.y) }

fun lineLoop(block: () -> Unit) {
    run {
        gl { glBegin(GL.GL_LINE_LOOP) }
        block()
        gl { glEnd() }
    }
}

fun point(block: () -> Unit) {
    run {
        gl { glBegin(GL.GL_POINTS) }
        block()
        gl { glEnd() }
    }
}

fun lineLoopOrPoint(isLineLoop: () -> Boolean, block: () -> Unit) = if (isLineLoop()) lineLoop(block) else point(block)

fun MouseEvent.toPoint() = Point4D(this.x.toDouble(), this.y.toDouble())
fun MouseEvent.isRightButton() = this.button == 1
