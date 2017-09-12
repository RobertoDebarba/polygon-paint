package br.furb.polygonpaint;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class World extends JFrame {

    private Camera camera;
    private GraphicalObject selectedGraphicalObject;
    private Color backgroundColor;
    private List<GraphicalObject> graphicalObjects;

    public World() {
        setTitle("Polygon Paint");
        setBounds(300, 250, 400, 400 + 22);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        GLCapabilities glCaps = new GLCapabilities();
        glCaps.setRedBits(8);
        glCaps.setBlueBits(8);
        glCaps.setGreenBits(8);
        glCaps.setAlphaBits(8);

        GLCanvas canvas = new GLCanvas(glCaps);
        add(canvas, BorderLayout.CENTER);
        canvas.requestFocus();
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
}
