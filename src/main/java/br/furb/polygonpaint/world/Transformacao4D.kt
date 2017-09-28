package br.furb.polygonpaint.world

/// \file br.furb.polygonpaint.world.Transformacao4D.java
/// \brief Classe que define as Transformacoes Geometricas no espaco 3D
/// \version $Revision: 1.7 $

/// \class br.furb.polygonpaint.world.Transformacao4D
/// \brief As Transformacoes usam coordenadas homogeneas
///
/// Uma matriz de Transformacao eh representada por uma matriz 4x4 que acumula trasnformacoes, isto eh, para aplicar as trasnformacoes T1, T2, em seguida, T3,
/// eh necessario multiplicar T1 * T2 * T3.
/// Os valores de Translacao estao na coluna mais a direita.

// Organizacao dos elementos internos da Matriz
//             [ matrix[0] matrix[4] matrix[8]  matrix[12] ]
// Transform = [ matrix[1] matrix[5] matrix[9]  matrix[13] ]
//             [ matrix[2] matrix[6] matrix[10] matrix[14] ]
//             [ matrix[3] matrix[7] matrix[11] matrix[15] ]

class Transformacao4D {

    /// \brief Cria uma matriz de Trasnformacao com uma matriz Identidade.
    private val matriz = doubleArrayOf(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0)

    /// Atribui os valores de uma matriz Identidade a matriz de Transformacao.
    fun atribuirIdentidade() {
        for (i in 0..15) {
            matriz[i] = 0.0
        }
        matriz[15] = 1.0
        matriz[10] = matriz[15]
        matriz[5] = matriz[10]
        matriz[0] = matriz[5]
    }

    /// Atribui os valores de Translacao (tx,ty,tz) a matriz de Transformacao.
    /// Elemento Neutro eh 0 (zero).
    fun atribuirTranslacao(tx: Double, ty: Double, tz: Double) {
        atribuirIdentidade()
        matriz[12] = tx
        matriz[13] = ty
        matriz[14] = tz
    }

    /// Atribui o valor de Escala (Ex,Ey,Ez) a matriz de Transformacao.
    /// Elemento Neutro eh 1 (um).
    /// Se manter os valores iguais de Ex,Ey e Ez o objeto vai ser reduzido ou ampliado proporcionalmente.
    fun atribuirEscala(sX: Double, sY: Double, sZ: Double) {
        atribuirIdentidade()
        matriz[0] = sX
        matriz[5] = sY
        matriz[10] = sZ
    }

    /// Atribui o valor de Rotacao (angulo) no eixo X a matriz de Transformacao.
    /// Elemento Neutro eh 0 (zero).
    fun atribuirRotacaoX(radians: Double) {
        atribuirIdentidade()
        matriz[5] = Math.cos(radians)
        matriz[9] = -Math.sin(radians)
        matriz[6] = Math.sin(radians)
        matriz[10] = Math.cos(radians)
    }

    /// Atribui o valor de Rotacao (angulo) no eixo Y a matriz de Transformacao.
    /// Elemento Neutro eh 0 (zero).
    fun atribuirRotacaoY(radians: Double) {
        atribuirIdentidade()
        matriz[0] = Math.cos(radians)
        matriz[8] = Math.sin(radians)
        matriz[2] = -Math.sin(radians)
        matriz[10] = Math.cos(radians)
    }

    /// Atribui o valor de Rotacao (angulo) no eixo Z a matriz de Transformacao.
    /// Elemento Neutro eh 0 (zero).
    fun atribuirRotacaoZ(radians: Double) {
        atribuirIdentidade()
        matriz[0] = Math.cos(radians)
        matriz[4] = -Math.sin(radians)
        matriz[1] = Math.sin(radians)
        matriz[5] = Math.cos(radians)
    }

    fun transformPoint(point: Point4D): Point4D {
        return Point4D(
                matriz[0] * point.x + matriz[4] * point.y + matriz[8] * point.z + matriz[12] * point.w,
                matriz[1] * point.x + matriz[5] * point.y + matriz[9] * point.z + matriz[13] * point.w,
                matriz[2] * point.x + matriz[6] * point.y + matriz[10] * point.z + matriz[14] * point.w,
                matriz[3] * point.x + matriz[7] * point.y + matriz[11] * point.z + matriz[15] * point.w)
    }

    fun transformMatrix(t: Transformacao4D): Transformacao4D {
        val result = Transformacao4D()
        for (i in 0..15)
            result.matriz[i] = matriz[i % 4] * t.matriz[i / 4 * 4] +
                    matriz[i % 4 + 4] * t.matriz[i / 4 * 4 + 1] +
                    matriz[i % 4 + 8] * t.matriz[i / 4 * 4 + 2] +
                    matriz[i % 4 + 12] * t.matriz[i / 4 * 4 + 3]
        return result
    }

    fun GetElement(index: Int): Double {
        return matriz[index]
    }

    fun SetElement(index: Int, value: Double) {
        matriz[index] = value
    }

    fun GetDate(): DoubleArray {
        return matriz
    }

    fun SetData(data: DoubleArray) {
        var i: Int

        i = 0
        while (i < 16) {
            matriz[i] = data[i]
            i++
        }
    }

    fun exibeMatriz() {
        println("______________________")
        println("|" + GetElement(0) + " | " + GetElement(4) + " | " + GetElement(8) + " | " + GetElement(12))
        println("|" + GetElement(1) + " | " + GetElement(5) + " | " + GetElement(9) + " | " + GetElement(13))
        println("|" + GetElement(2) + " | " + GetElement(6) + " | " + GetElement(10) + " | " + GetElement(14))
        println("|" + GetElement(3) + " | " + GetElement(7) + " | " + GetElement(11) + " | " + GetElement(15))
    }

    companion object {
        internal val DEG_TO_RAD = 0.017453292519943295769236907684886
    }


}