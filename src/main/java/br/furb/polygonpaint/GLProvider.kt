package br.furb.polygonpaint

import javax.media.opengl.GL
import javax.media.opengl.GLAutoDrawable
import javax.media.opengl.glu.GLU

object GLProvider {
    lateinit var gl: GL
    lateinit var glu: GLU
    lateinit var glDrawable: GLAutoDrawable
}