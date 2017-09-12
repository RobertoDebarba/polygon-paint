package br.furb.polygonpaint;

import java.util.List;

public class World {

    private Camera camera;
    private GraphicalObject selectedGraphicalObject;
    private Color backgroundColor;
    private List<GraphicalObject> graphicalObjects;

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
