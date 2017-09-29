package br.furb.polygonpaint.world

import br.furb.polygonpaint.gl
import br.furb.polygonpaint.glColor
import br.furb.polygonpaint.lineLoop
import br.furb.polygonpaint.world.attributes.Color.RED

class BoundingBox {

    private var menorX: Double
    private var menorY: Double
    private var menorZ: Double
    private var maiorX: Double
    private var maiorY: Double
    private var maiorZ: Double

    constructor(menorX: Double = 0.0, menorY: Double = 0.0, menorZ: Double = 0.0, maiorX: Double = 0.0, maiorY: Double = 0.0, maiorZ: Double = 0.0) {
        this.menorX = menorX
        this.menorY = menorY
        this.menorZ = menorZ
        this.maiorX = maiorX
        this.maiorY = maiorY
        this.maiorZ = maiorZ
        this.centro = Point4D(0.toDouble(), 0.toDouble())
    }

    constructor(point: Point4D) : this(point.x, point.y, point.z)

    val centro: Point4D

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

    fun desenharOpenGLBBox() {
        gl {
            glColor(RED)
            lineLoop {
                glVertex3d(menorX, maiorY, menorZ)
                glVertex3d(maiorX, maiorY, menorZ)
                glVertex3d(maiorX, menorY, menorZ)
                glVertex3d(menorX, menorY, menorZ)
            }
        }
    }

    fun isInternal(point: Point4D): Boolean {
        return point.x in menorX..maiorX && point.y in menorY..maiorY && point.z in menorZ..maiorZ
    }

}

