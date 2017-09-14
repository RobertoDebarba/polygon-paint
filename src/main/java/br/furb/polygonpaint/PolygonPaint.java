package br.furb.polygonpaint;

import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.swing.*;
import java.awt.*;

public class PolygonPaint extends JFrame {
    public PolygonPaint() {
        World world = new World();

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
        canvas.addGLEventListener(world);
        canvas.addMouseListener(world);
        canvas.requestFocus();
    }

    public static void main(String[] args) {
        new PolygonPaint().setVisible(true);
    }
}
