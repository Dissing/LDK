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

package volpes.ldk.client.opengl;

/**
 * @author Lasse Dissing Hansen
 */
public class TexturedVertex {

    public static final int elementBytes = 4;

    public static final int positionElementCount = 4;
    public static final int colorElementCount = 4;
    public static final int textureElementCount = 2;

    public static final int positionByteCount = positionElementCount * elementBytes;
    public static final int colorByteCount = colorElementCount * elementBytes;
    public static final int textureByteCount = textureElementCount * elementBytes;

    public static final int positionOffset = 0;
    public static final int colorOffset = positionOffset + positionByteCount;
    public static final int textureOffset = colorOffset + colorByteCount;

    public static final int elementCount = positionElementCount + colorByteCount + textureByteCount;
    public static final int stride = positionByteCount + colorByteCount + textureByteCount;


    private float[] xyzw = new float[] {0f,0f,0f,0f};
    private float[] rgba = new float[] {1f,1f,1f,1f};
    private float[] st = new float[] {0f,0f};

    public void setXYZ(float x, float y, float z) {
        setXYZW(x,y,z,1f);
    }

    public void setRGB(float r, float g, float b) {
        setRGBA(r,g,b,1f);
    }

    public void setXYZW(float x, float y, float z, float w) {
        //Certainly not the most pretty way, but sure faster than allocating a new array
        xyzw[0] = x;
        xyzw[1] = y;
        xyzw[2] = z;
        xyzw[3] = w;
    }

    public void setRGBA(float r, float g, float b, float a) {
        rgba[0] = r;
        rgba[1] = g;
        rgba[2] = b;
        rgba[3] = a;

    }

    public void setST(float s, float t) {
        st[0] = s;
        st[1] = t;
    }

    public float[] getXYZW() {
        return xyzw.clone();
    }

    public float[] getRGBA() {
        return rgba.clone();
    }

    public float[] getST() {
        return st.clone();
    }

    public float[] getElements() {
        float[] elements = new float[elementCount];
        int i = 0;

        //I think loop unrolling would be overkill here. Please fix if otherwise.
        for (float element : xyzw) {
            elements[i++] = element;
        }
        for (float element : rgba) {
            elements[i++] = element;
        }
        for (float element : st) {
            elements[i++] = element;
        }
        return elements;
    }



}
