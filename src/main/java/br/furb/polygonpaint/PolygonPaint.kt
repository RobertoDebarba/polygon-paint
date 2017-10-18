package br.furb.polygonpaint

import br.furb.polygonpaint.world.World
import br.furb.polygonpaint.world.interactions.InsertionPolygonAction
import java.awt.BorderLayout
import java.awt.Cursor
import javax.media.opengl.*
import javax.media.opengl.glu.GLU
import javax.swing.JFrame
import javax.swing.WindowConstants


class PolygonPaint : JFrame(), GLEventListener {

    var world: World = World()

    /**
     * Na inicialização do Formulario Cria o formulario com um canvas e inicializa com a ação de iserir poligono
     */
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

        InsertionPolygonAction(world, canvas)
        canvas.requestFocus()
    }

    /**
     * Inicializa as variaveis do OpenGl
     */
    override fun init(glAutoDrawable: GLAutoDrawable) {
        GLProvider.glDrawable = glAutoDrawable
        GLProvider.gl = glAutoDrawable.gl
        GLProvider.glu = GLU()
        GLProvider.glDrawable.gl = DebugGL(GLProvider.gl)
        GLProvider.gl.glClearColor(world.backgroundColor.red, world.backgroundColor.green, world.backgroundColor.blue, 1.0f)
    }

    /**
     * Cada solicitação de atualização de frame do OpenGl redesenha o mundo
     */
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

}

/**
 * Função de inicialização do kotlin só chama o FORM
 */
fun main(args: Array<String>) {
    PolygonPaint().isVisible = true
}
