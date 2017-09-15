package br.furb.polygonpaint

import javax.media.opengl.GL

enum class GraphicalPrimitive constructor(val id: Int) {

    LINE_STRIP(GL.GL_LINE_STRIP), LINE_LOOP(GL.GL_LINE_LOOP)
}
