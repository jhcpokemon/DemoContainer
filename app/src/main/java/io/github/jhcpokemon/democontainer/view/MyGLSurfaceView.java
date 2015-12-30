package io.github.jhcpokemon.democontainer.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import io.github.jhcpokemon.democontainer.model.MyCube;

public class MyGLSurfaceView extends GLSurfaceView {
    private MyRender render;

    public MyGLSurfaceView(Context context) {
        super(context);
        render = new MyRender();
        setRenderer(render);
        setRenderMode(RENDERMODE_WHEN_DIRTY);
    }

    public MyRender getRender() {
        return render;
    }

    public class MyRender implements GLSurfaceView.Renderer {
        private MyCube cube = new MyCube();
        private GL10 gl;

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            gl.glEnable(GL10.GL_DEPTH_TEST);
            gl.glEnable(GL10.GL_LIGHT0);
            gl.glEnable(GL10.GL_LIGHTING);
            gl.glDepthFunc(GL10.GL_LEQUAL);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            gl.glViewport(0, 0, width, height);
            gl.glMatrixMode(GL10.GL_PROJECTION);
            gl.glLoadIdentity();
            GLU.gluPerspective(gl, 45f, (float) width / height, 1f, 50f);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            setGL(gl);
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
            gl.glTranslatef(0, 0, -3f);
            gl.glRotatef(30f, 0, 1, 0);
            cube.draw(gl);
        }

        public void setGL(GL10 gl) {
            this.gl = gl;
        }

        public void reDraw(float rotate, int type) {
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            gl.glLoadIdentity();
            gl.glTranslatef(0, 0, -3f);
            if (type == 0) {
                gl.glRotatef(rotate, 0, 1, 0);
            } else if (type == 1) {
                gl.glRotatef(rotate, 1, 0, 0);
            }
            cube.draw(gl);
        }
    }
}


