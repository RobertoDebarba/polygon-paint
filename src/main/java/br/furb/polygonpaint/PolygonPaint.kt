package br.furb.polygonpaint

import javax.media.opengl.*
import javax.media.opengl.glu.GLU
import javax.swing.*
import java.awt.*
import java.awt.event.*
import java.awt.event.ComponentEvent
import java.awt.event.ComponentListener



class PolygonPaint : JFrame(), GLEventListener, MouseListener, MouseMotionListener, ComponentListener  {

    var world :World = World()

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
        cursor = Cursor(Cursor.HAND_CURSOR)
        val canvas = GLCanvas(glCaps)
        add(canvas, BorderLayout.CENTER)
        canvas.addGLEventListener(this)
        canvas.addMouseListener(this)
        canvas.addMouseMotionListener(this)
        canvas.requestFocus()

        addComponentListener(this)
    }

    override fun init(glAutoDrawable: GLAutoDrawable) {
        GLProvider.glDrawable = glAutoDrawable
        GLProvider.gl = glAutoDrawable.gl
        GLProvider.glu = GLU()
        GLProvider.glDrawable.gl = DebugGL(GLProvider.gl)
        GLProvider.gl.glClearColor(world.backgroundColor.red, world.backgroundColor.green, world.backgroundColor.blue, 1.0f)
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

    override fun componentMoved(e: ComponentEvent?) {
    }

    override fun componentResized(e: ComponentEvent?) {
        world.rezise(this.width, this.height -22)
    }

    override fun componentHidden(e: ComponentEvent?) {
    }

    override fun componentShown(e: ComponentEvent?) {
    }

}

fun main(args: Array<String>) {
    PolygonPaint().isVisible = true
}
