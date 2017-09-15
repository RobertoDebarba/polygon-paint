package br.furb.polygonpaint

import javax.media.opengl.GL

fun gl(block: GL.() -> Unit) = GLProvider.gl.block()

fun glColor(color: Color) = gl { glColor3f(color.red, color.green, color.blue) }

fun glPoint(point: Point4D) = gl { glVertex2d(point.x, point.y) }

fun lineLoop(block: () -> Unit) = {
    gl { glBegin(GL.GL_LINE_LOOP) }
    block()
    gl { glEnd() }
}

fun point(block: () -> Unit) = {
    gl { glBegin(GL.GL_POINTS) }
    block()
    gl { glEnd() }
}

fun lineLoopOrPoint(isLineLoop: () -> Boolean, block: () -> Unit) = if (isLineLoop()) lineLoop(block) else point(block)