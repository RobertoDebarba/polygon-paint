package br.furb.polygonpaint

import javax.media.opengl.*
import javax.media.opengl.glu.GLU
import javax.swing.*
import java.awt.*
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener

class PolygonPaint : JFrame(), GLEventListener, MouseListener, MouseMotionListener  {
    lateinit var world :World

    init {

        title = "Polygon Paint"
        setBounds(300, 250, 400, 400 + 22)
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        contentPane.layout = BorderLayout()

        val glCaps = GLCapabilities()
        glCaps.redBits = 8
        glCaps.blueBits = 8
        glCaps.greenBits = 8
        glCaps.alphaBits = 8

        val canvas = GLCanvas(glCaps)
        add(canvas, BorderLayout.CENTER)
        canvas.addGLEventListener(this)
        canvas.addMouseListener(this)
        canvas.addMouseMotionListener(this)
        canvas.requestFocus()
    }

    override fun init(glAutoDrawable: GLAutoDrawable) {
        GLProvider.glDrawable = glAutoDrawable
        GLProvider.gl = glAutoDrawable.gl
        GLProvider.glu = GLU()
        GLProvider.glDrawable.gl = DebugGL(GLProvider.gl)
        world = World()
    }

    override fun display(glAutoDrawable: GLAutoDrawable) {
        GLProvider.gl.glClear(GL.GL_COLOR_BUFFER_BIT)
        GLProvider.gl.glMatrixMode(GL.GL_MODELVIEW)
        GLProvider.gl.glLoadIdentity()
        world.draw()
        GLProvider.gl.glFlush()
    }

    override fun reshape(glAutoDrawable: GLAutoDrawable, i: Int, i1: Int, width: Int, height: Int) {
        GLProvider.gl.glMatrixMode(GL.GL_PROJECTION)
        GLProvider.gl.glLoadIdentity()
        GLProvider.gl.glViewport(0, 0, width, height)
    }

    override fun displayChanged(glAutoDrawable: GLAutoDrawable, b: Boolean, b1: Boolean) {

    }

    override fun mouseClicked(e: MouseEvent) {
        world.mouseClicked(e)
        GLProvider.glDrawable.display()
    }

    override fun mousePressed(e: MouseEvent) {

    }

    override fun mouseReleased(e: MouseEvent) {

    }

    override fun mouseEntered(e: MouseEvent) {

    }

    override fun mouseExited(e: MouseEvent) {

    }

    override fun mouseDragged(e: MouseEvent) {

    }

    override fun mouseMoved(e: MouseEvent) {
        var draw = false
        draw = world.mouseMoved(e) || draw


        if(draw)
            GLProvider.glDrawable.display()
    }

}

fun main(args: Array<String>) {
    PolygonPaint().isVisible = true
}
