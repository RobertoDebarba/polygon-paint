package br.furb.polygonpaint

data class Point4D(var x: Double,
              var y: Double,
              var z: Double = 0.0,
              var w: Double = 1.0){

    fun getPair() = x to y

    fun getTriple() = x to y to z

    companion object {
        fun invertPoint(pto : Point4D){
            pto.x = pto.x *-1
            pto.y = pto.y *-1
            pto.z = pto.z *-1
            pto.w = pto.w *-1
        }

    }
}
