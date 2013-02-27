/*
 * Copyright (C) 2013 Lasse Dissing Hansen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package volpes.ldk.client;

import volpes.ldk.client.opengl.LWJGLRender;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Lasse Dissing Hansen
 */
public class OpenGLRender extends Render {

    LWJGLRender render;

    public OpenGLRender() {
        render = new LWJGLRender();
    }

    @Override
    protected void initScreen() {
        render.initializeScreen();
    }

    @Override
    protected void initRender() {
        render.initializeRender();
    }

    @Override
    protected void destroyRender() {
        render.destroyRender();
    }

    @Override
    protected void destroyScreen() {
        render.destroyScreen();
    }

    @Override
    protected void attachInput(Input input) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void updateSettings() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void preRender() {
        render.clearScreen();
        render.render();
    }

    @Override
    protected void postRender() {
        render.show();

    }

    @Override
    public void drawImage(BufferedImage img, int x, int y) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void drawString(String str, int x, int y) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void drawRect(int x, int y, int width, int height) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void fillRect(int x, int y, int width, int height) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setColor(Color color) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void scale(float sx, float sy) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void translate(int tx, int ty) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void rotate(double theta) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void rotate(double theta, int x, int y) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void push() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void pop() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void fillPolygon(Polygon polygon) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
