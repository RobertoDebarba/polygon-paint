/// \file Transformacao4D.java
/// \brief Classe que define as Transformacoes Geometricas no espaco 3D
/// \version $Revision: 1.7 $

/// \class Transformacao4D
/// \brief As Transformacoes usam coordenadas homogeneas
///
/// Uma matriz de Transformacao eh representada por uma matriz 4x4 que acumula trasnformacoes, isto eh, para aplicar as trasnformacoes T1, T2, em seguida, T3,
/// eh necessario multiplicar T1 * T2 * T3. 
/// Os valores de Translacao estao na coluna mais a direita.

import java.lang.Math;

// Organizacao dos elementos internos da Matriz
//             [ matrix[0] matrix[4] matrix[8]  matrix[12] ]
// Transform = [ matrix[1] matrix[5] matrix[9]  matrix[13] ]
//             [ matrix[2] matrix[6] matrix[10] matrix[14] ]
//             [ matrix[3] matrix[7] matrix[11] matrix[15] ]

public final class Transformacao4D {
	static final double DEG_TO_RAD = 0.017453292519943295769236907684886;

    /// \brief Cria uma matriz de Trasnformacao com uma matriz Identidade.
	private double[] matriz = {	
			1, 0, 0, 0,
			0, 1, 0, 0,
			0, 0, 1, 0,
			0, 0, 0, 1};

	public Transformacao4D() {
	}

	/// Atribui os valores de uma matriz Identidade a matriz de Transformacao.
	public void atribuirIdentidade() {
		for (int i=0; i<16; ++i) {
			matriz[i] = 0.0;
		}
		matriz[0] = matriz[5] = matriz[10] = matriz[15] = 1.0;
	}

	/// Atribui os valores de Translacao (tx,ty,tz) a matriz de Transformacao.
	/// Elemento Neutro eh 0 (zero).
	public void atribuirTranslacao(double tx, double ty, double tz)
	{
	    atribuirIdentidade();
	    matriz[12] = tx;
	    matriz[13] = ty;
	    matriz[14] = tz;
	}

	/// Atribui o valor de Escala (Ex,Ey,Ez) a matriz de Transformacao.
	/// Elemento Neutro eh 1 (um).
	/// Se manter os valores iguais de Ex,Ey e Ez o objeto vai ser reduzido ou ampliado proporcionalmente.
	public void atribuirEscala(double sX, double sY, double sZ)
	{
	    atribuirIdentidade();
	    matriz[0] =  sX;
	    matriz[5] =  sY;
	    matriz[10] = sZ;
	}
	
	/// Atribui o valor de Rotacao (angulo) no eixo X a matriz de Transformacao.
	/// Elemento Neutro eh 0 (zero).
	public void atribuirRotacaoX(double radians)
	{
	    atribuirIdentidade();
	    matriz[5] =   Math.cos(radians);
	    matriz[9] =  -Math.sin(radians);
	    matriz[6] =   Math.sin(radians);
	    matriz[10] =  Math.cos(radians);
	}

	/// Atribui o valor de Rotacao (angulo) no eixo Y a matriz de Transformacao.
	/// Elemento Neutro eh 0 (zero).
	public void atribuirRotacaoY(double radians)
	{
	    atribuirIdentidade();
	    matriz[0] =   Math.cos(radians);
	    matriz[8] =   Math.sin(radians);
	    matriz[2] =  -Math.sin(radians);
	    matriz[10] =  Math.cos(radians);
	}

	/// Atribui o valor de Rotacao (angulo) no eixo Z a matriz de Transformacao.
	/// Elemento Neutro eh 0 (zero).
	public void atribuirRotacaoZ(double radians)
	{
	    atribuirIdentidade();
	    matriz[0] =  Math.cos(radians);
	    matriz[4] = -Math.sin(radians);
	    matriz[1] =  Math.sin(radians);
	    matriz[5] =  Math.cos(radians);
	}

	public Ponto4D transformPoint(Ponto4D point) {
		Ponto4D pointResult = new Ponto4D(
				matriz[0]*point.obterX()  + matriz[4]*point.obterY() + matriz[8]*point.obterZ()  + matriz[12]*point.obterW(),
				matriz[1]*point.obterX()  + matriz[5]*point.obterY() + matriz[9]*point.obterZ()  + matriz[13]*point.obterW(),
				matriz[2]*point.obterX()  + matriz[6]*point.obterY() + matriz[10]*point.obterZ() + matriz[14]*point.obterW(),
                matriz[3]*point.obterX()  + matriz[7]*point.obterY() + matriz[11]*point.obterZ() + matriz[15]*point.obterW());
		return pointResult;
	}

	public Transformacao4D transformMatrix(Transformacao4D t) {
		Transformacao4D result = new Transformacao4D();
	    for (int i=0; i < 16; ++i)
        result.matriz[i] =
              matriz[i%4]    *t.matriz[i/4*4]  +matriz[(i%4)+4] *t.matriz[i/4*4+1]
            + matriz[(i%4)+8]*t.matriz[i/4*4+2]+matriz[(i%4)+12]*t.matriz[i/4*4+3];
		return result;
	}
	
	public double GetElement(int index) {
		return matriz[index];
	}
	
	public void SetElement(int index, double value) {
		matriz[index] = value;
	}

	public double[] GetDate() {
		return matriz;	
	}
	
	public void SetData(double[] data)
	{
	    int i;

	    for (i=0; i<16; i++)
	    {
	        matriz[i] = (data[i]);
	    }
	}

	public void exibeMatriz() {
		System.out.println("______________________");
		System.out.println("|" + GetElement( 0) + " | "+ GetElement( 4) + " | " + GetElement( 8) + " | "+ GetElement(12));
		System.out.println("|" + GetElement( 1) + " | "+ GetElement( 5) + " | " + GetElement( 9) + " | "+ GetElement(13));
		System.out.println("|" + GetElement( 2) + " | "+ GetElement( 6) + " | " + GetElement(10) + " | "+ GetElement(14));
		System.out.println("|" + GetElement( 3) + " | "+ GetElement( 7) + " | " + GetElement(11) + " | "+ GetElement(15));
	}

	
}