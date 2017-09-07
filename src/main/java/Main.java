/// \file Exemplo_N2_Jogl_Eclipse.java
/// \brief Exemplo_N2_Jogl_Eclipse: desenha uma linha na diagonal.
/// \version $Revision: 1.0 $
/// \author Dalton Reis.
/// \date 03/05/13.
/// Obs.: variaveis globais foram usadas por questoes didaticas mas nao sao recomendas para aplicacoes reais.

import javax.media.opengl.DebugGL;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import java.awt.event.*;

public class Main implements GLEventListener, KeyListener, MouseListener, MouseMotionListener {
    private GL gl;
    private GLU glu;
    private GLAutoDrawable glDrawable;
    private Ponto4D pto1 = new Ponto4D(0.0, 0.0, 0.0, 1.0);
    private Ponto4D pto2 = new Ponto4D(200.0, 200.0, 0.0, 1.0);
    private Camera camera = new Camera(-400.0f, 400.0f, -400.0f, 400.0f);

    private float horizontal = 0;
    private float raio = 100;
    private float angulo = 45;

    public void init(GLAutoDrawable drawable) {
        System.out.println(" --- init ---");
        glDrawable = drawable;
        gl = drawable.getGL();
        glu = new GLU();
        glDrawable.setGL(new DebugGL(gl));
        System.out.println("Espaco de desenho com tamanho: " + drawable.getWidth() + " x " + drawable.getHeight());
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    //exibicaoPrincipal
    public void display(GLAutoDrawable arg0) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        glu.gluOrtho2D(camera.getLeft(), camera.getRight(), camera.getDown(), camera.getTop());

        SRU();

        // seu desenho ...
        gl.glColor3f(0.0f, 0.0f, 0.0f);
        gl.glLineWidth(3.0f);
        gl.glColor3f(0.0f, 0.0f, 0f);

        gl.glBegin(GL.GL_LINE_STRIP);
        gl.glVertex2d(horizontal, 0);
        gl.glVertex2d(retornaX(angulo, raio) + horizontal, retornaY(angulo, raio));
        gl.glEnd();

        gl.glFlush();
    }

    public void keyPressed(KeyEvent e) {
        System.out.println(" --- keyPressed ---");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_I:
                if (camera.getTop() <= 50) break;

                camera.setTop(camera.getTop()-10);
                camera.setDown(camera.getDown()+10);
                camera.setLeft(camera.getLeft()+10);
                camera.setRight(camera.getRight()-10);
                glDrawable.display();
                break;

            case KeyEvent.VK_O:
                if (camera.getTop() >= 680) break;

                camera.setTop(camera.getTop()+10);
                camera.setDown(camera.getDown()-10);
                camera.setLeft(camera.getLeft()-10);
                camera.setRight(camera.getRight()+10);
                glDrawable.display();
                break;

            case KeyEvent.VK_E:
                if (camera.getTop() >= 680) break;

                camera.setLeft(camera.getLeft()-10);
                camera.setRight(camera.getRight()-10);
                glDrawable.display();
                break;

            case KeyEvent.VK_D:
                if (camera.getTop() >= 680) break;

                camera.setLeft(camera.getLeft()+10);
                camera.setRight(camera.getRight()+10);
                glDrawable.display();
                break;

            case KeyEvent.VK_C:
                if (camera.getTop() >= 680) break;

                camera.setTop(camera.getTop()+10);
                camera.setDown(camera.getDown()+10);
                glDrawable.display();
                break;

            case KeyEvent.VK_B:
                if (camera.getTop() >= 680) break;

                camera.setTop(camera.getTop()-10);
                camera.setDown(camera.getDown()-10);
                glDrawable.display();
                break;

            case KeyEvent.VK_Q:
                horizontal -= 10;
                glDrawable.display();
                break;

            case KeyEvent.VK_W:
                horizontal += 10;
                glDrawable.display();
                break;

            case KeyEvent.VK_A:
                raio -= 2;
                glDrawable.display();
                break;

            case KeyEvent.VK_S:
                raio += 2;
                glDrawable.display();
                break;

            case KeyEvent.VK_Z:
                angulo -= 2;
                glDrawable.display();
                break;

            case KeyEvent.VK_X:
                angulo += 2;
                glDrawable.display();
                break;
        }
    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        System.out.println(" --- reshape ---");
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glViewport(0, 0, width, height);
    }

    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
        System.out.println(" --- displayChanged ---");
    }

    public void keyReleased(KeyEvent arg0) {
        System.out.println(" --- keyReleased ---");
    }

    public void keyTyped(KeyEvent arg0) {
        System.out.println(" --- keyTyped ---");
    }

    public void SRU() {
//		gl.glDisable(gl.GL_TEXTURE_2D);
//		gl.glDisableClientState(gl.GL_TEXTURE_COORD_ARRAY);
//		gl.glDisable(gl.GL_LIGHTING); //TODO: [D] FixMe: check if lighting and texture is enabled

        // eixo x
        gl.glColor3f(1.0f, 0.0f, 0.0f);
        gl.glLineWidth(1.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(-200.0f, 0.0f);
        gl.glVertex2f(200.0f, 0.0f);
        gl.glEnd();
        // eixo y
        gl.glColor3f(0.0f, 1.0f, 0.0f);
        gl.glBegin(GL.GL_LINES);
        gl.glVertex2f(0.0f, -200.0f);
        gl.glVertex2f(0.0f, 200.0f);
        gl.glEnd();
    }

    public double retornaX(double angulo, double raio) {
        return (raio * Math.cos(Math.PI * angulo / 180.0));
    }

    public double retornaY(double angulo, double raio) {
        return (raio * Math.sin(Math.PI * angulo / 180.0));
    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}