package br.furb.polygonpaint.world

import sun.reflect.generics.reflectiveObjects.NotImplementedException

class Camera(var left: Double, var right: Double, var down: Double, var top: Double) {

    fun zoomIn() {
        throw NotImplementedException()
    }

    fun zoomOut() {
        throw NotImplementedException()
    }

    fun pam() {
        throw NotImplementedException()
    }
}
