package br.furb.polygonpaint;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Camera {

    private float left;
    private float right;
    private float down;
    private float top;

    public Camera(float left, float right, float down, float top) {
        this.left = left;
        this.right = right;
        this.down = down;
        this.top = top;
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getDown() {
        return down;
    }

    public void setDown(float down) {
        this.down = down;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }

    public void zoomIn() {
        throw new NotImplementedException();
    }

    public void zoomOut() {
        throw new NotImplementedException();
    }

    public void pam() {
        throw new NotImplementedException();
    }
}
