package br.furb.polygonpaint;

import javax.media.opengl.GL;

public enum GraphicalPrimitive {

    LINE_STRIP(GL.GL_LINE_STRIP), LINE_LOOP(GL.GL_LINE_LOOP);

    private int id;

    GraphicalPrimitive(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
