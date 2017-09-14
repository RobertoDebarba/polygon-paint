package br.furb.polygonpaint;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.media.opengl.GL;
import java.util.ArrayList;
import java.util.List;

public class GraphicalObject {
    private Color color;
    private GraphicalPrimitive graphicalPrimitive;
    private List<Point4D> points;
    private BoundingBox boundingBox;
    private Transformacao4D transformation;
    private boolean ehAlter;

    public GraphicalObject() {
        setGraphicalPrimitive(GraphicalPrimitive.LINE_LOOP);
        setColor(new Color(0, 0, 0));
        setPoints(new ArrayList<>());
        ehAlter = true;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public GraphicalPrimitive getGraphicalPrimitive() {
        return graphicalPrimitive;
    }

    public void setGraphicalPrimitive(GraphicalPrimitive graphicalPrimitive) {
        this.graphicalPrimitive = graphicalPrimitive;
    }

    public List<Point4D> getPoints() {
        return points;
    }

    private void setPoints(List<Point4D> points) {
        this.points = points;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    private void setBoundingBox(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    public Transformacao4D getTransformation() {
        return transformation;
    }

    public void setTransformation(Transformacao4D transformation) {
        this.transformation = transformation;
    }

    public void selectPolygon() {
        throw new NotImplementedException();
    }

    public void removePolygon() {
        throw new NotImplementedException();
    }

    public void draw(GL gl) {
        gl.glColor3f(getColor().getRed(), getColor().getGreen(), getColor().getBlue());
        gl.glLineWidth(3.0f);
        gl.glBegin(GL.GL_LINES);
        for (Point4D pt : points) {
            gl.glVertex4d(pt.obterX(), pt.obterY(), pt.obterZ(), pt.obterW());
        }
        gl.glEnd();
    }

    public boolean getEhAlter() {
        return ehAlter;
    }

    public void addPoint(Point4D point4D) {
        points.add(point4D);
    }
}

