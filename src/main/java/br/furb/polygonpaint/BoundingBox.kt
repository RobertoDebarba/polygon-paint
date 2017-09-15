package br.furb.polygonpaint

import javax.media.opengl.GL

class BoundingBox constructor(var menorX: Double = 0.0,
                              var menorY: Double = 0.0,
                              var menorZ: Double = 0.0,
                              var maiorX: Double = 0.0,
                              var maiorY: Double = 0.0,
                              var maiorZ: Double = 0.0) {

    override fun toString(): String {
        return super.toString()
    }

    val centro : Point4D = Point4D(0.toDouble(), 0.toDouble())

    fun atribuirBoundingBox(smallerX: Double, smallerY: Double, smallerZ: Double, greaterX: Double, greaterY: Double, greaterZ: Double) {
        this.menorX = smallerX
        this.menorY = smallerY
        this.menorZ = smallerZ
        this.maiorX = greaterX
        this.maiorY = greaterY
        this.maiorZ = greaterZ
        processarCentroBBox()
    }

    fun atualizarBBox(point: Point4D) {
        atualizarBBox(point.x, point.y, point.z)
    }

    fun atualizarBBox(x: Double, y: Double, z: Double) {
        if (x < menorX)
            menorX = x
        else {
            if (x > maiorX) maiorX = x
        }
        if (y < menorY)
            menorY = y
        else {
            if (y > maiorY) maiorY = y
        }
        if (z < menorZ)
            menorZ = z
        else {
            if (z > maiorZ) maiorZ = z
        }
    }

    fun processarCentroBBox() {
        centro.x = (maiorX + menorX) / 2
        centro.y = (maiorY + menorY) / 2
        centro.z = (maiorZ + menorZ) / 2
    }

    fun desenharOpenGLBBox(gl: GL) {
        gl.glColor3f(1.0f, 0.0f, 0.0f)

        gl.glBegin(GL.GL_LINE_LOOP)
        gl.glVertex3d(menorX, maiorY, menorZ)
        gl.glVertex3d(maiorX, maiorY, menorZ)
        gl.glVertex3d(maiorX, menorY, menorZ)
        gl.glVertex3d(menorX, menorY, menorZ)
        gl.glEnd()
    }

}

