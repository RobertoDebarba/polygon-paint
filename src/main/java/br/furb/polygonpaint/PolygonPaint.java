package br.furb.polygonpaint;

import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PolygonPaint extends JFrame {
    private PolygonPaint() {
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
        canvas.addMouseMotionListener(world);
        canvas.requestFocus();
    }

    public static void main(String[] args) {
        new PolygonPaint().setVisible(true);
    }

}
