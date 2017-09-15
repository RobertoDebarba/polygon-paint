package br.furb.polygonpaint

import javax.media.opengl.*
import javax.media.opengl.glu.GLU
import javax.swing.*
import java.awt.*
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.awt.event.MouseMotionListener

class PolygonPaint constructor() : JFrame() {
    init {
        val world = World()

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
        canvas.addGLEventListener(world)
        canvas.addMouseListener(world)
        canvas.addMouseMotionListener(world)
        canvas.requestFocus()
    }
}

fun main(args: Array<String>) {
    PolygonPaint().isVisible = true
}
