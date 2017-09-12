package br.furb.polygonpaint;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

public class GraphicalObject {
    private Color color;
    private GraphicalPrimitive graphicalPrimitive;
    private List<Point4D> points;
    private BoundingBox boundingBox;
    private Transformacao4D transformation;

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

    public void setPoints(List<Point4D> points) {
        this.points = points;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(BoundingBox boundingBox) {
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

    public void draw() {
        throw new NotImplementedException();
    }
}

