package br.furb.polygonpaint;

import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class World implements GLEventListener, MouseListener, MouseMotionListener {

    private GL gl;
    private GLU glu;
    private GLAutoDrawable glDrawable;

    private Camera camera;
    private GraphicalObject selectedGraphicalObject;
    private Color backgroundColor;
    private List<GraphicalObject> graphicalObjects;

    public World() {
        setBackgroundColor(new Color(1, 1, 1));
        setCamera(new Camera(0, 400, 0, 400));
        setGraphicalObjects(new ArrayList<>());
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public GraphicalObject getSelectedGraphicalObject() {
        return selectedGraphicalObject;
    }

    public void setSelectedGraphicalObject(GraphicalObject selectedGraphicalObject) {
        this.selectedGraphicalObject = selectedGraphicalObject;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public List<GraphicalObject> getGraphicalObjects() {
        return graphicalObjects;
    }

    public void setGraphicalObjects(List<GraphicalObject> graphicalObjects) {
        this.graphicalObjects = graphicalObjects;
    }

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        glDrawable = glAutoDrawable;
        gl = glAutoDrawable.getGL();
        glu = new GLU();
        glDrawable.setGL(new DebugGL(gl));
        gl.glClearColor(getBackgroundColor().getRed(), getBackgroundColor().getGreen(), getBackgroundColor().getBlue(), 1.0f);
    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);

        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        //glu.gluOrtho2D(-400.0f, 400.0f, -400.0f, 400.0f);
        glu.gluOrtho2D(getCamera().getLeft(), getCamera().getRight(), getCamera().getTop(), getCamera().getDown());

        // seu desenho ...
        for (GraphicalObject obj : graphicalObjects) {
            obj.draw(gl);
        }

        gl.glFlush();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int width, int height) {
        System.out.println(" --- reshape ---");
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glViewport(0, 0, width, height);
    }

    @Override
    public void displayChanged(GLAutoDrawable glAutoDrawable, boolean b, boolean b1) {

    }

    private int MOUSE_RIGHT_BUTON = 1;
    private int MOUSE_LEFT_BUTON = 3;

    @Override
    public void mouseClicked(MouseEvent e) {
        if (getSelectedGraphicalObject() != null && getSelectedGraphicalObject().getEhAlter()) {
            getSelectedGraphicalObject().addPoint(new Point4D(e.getX(), e.getY(), 0, 1));
        } else {
            if (e.getButton() == MOUSE_RIGHT_BUTON) {
                GraphicalObject graphObject = new GraphicalObject();
                graphicalObjects.add(graphObject);
                setSelectedGraphicalObject(graphObject);
                graphObject.addPoint(new Point4D(e.getX(), e.getY(), 0, 1));
                graphObject.addPoint(new Point4D(e.getX(), e.getY(), 0, 1));
            }
        }
        glDrawable.display();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (getSelectedGraphicalObject() != null && getSelectedGraphicalObject().getEhAlter()) {
            getSelectedGraphicalObject().getSelectedPoint().atribuirX(e.getX());
            getSelectedGraphicalObject().getSelectedPoint().atribuirY(e.getY());
            glDrawable.display();
        }
    }
}
