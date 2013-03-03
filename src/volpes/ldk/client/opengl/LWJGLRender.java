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

import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;
import volpes.ldk.utils.VFS;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * @author Lasse Dissing Hansen
 */
public class LWJGLRender {

    private int programId;

    private Screen screen;

    int vaoID;
    int vboID;
    int vboiID;
    int indiceCount;
    int texID;


    public LWJGLRender() {

    }

    public void destroyScreen() {
        screen.destroy();
    }

    public void destroyRender() {
        ARBShaderObjects.glUseProgramObjectARB(0);
    }

    public void initializeScreen() {
        screen = new Screen();
    }

    public void initializeRender() {
        glViewport(0,0,screen.getWidth(),screen.getHeight());
        glClearColor(0.4f,0.6f,0.9f,0f);
        int vertShader = 0;
        int fragShader = 0;
        if (vertShader == 0 || fragShader == 0)
            return;
        programId = ARBShaderObjects.glCreateProgramObjectARB();

        if (programId == 0)
            return;

        ARBShaderObjects.glAttachObjectARB(programId,vertShader);
        ARBShaderObjects.glAttachObjectARB(programId,fragShader);

        ARBShaderObjects.glLinkProgramARB(programId);

        if (ARBShaderObjects.glGetObjectParameteriARB(programId,ARBShaderObjects.GL_OBJECT_LINK_STATUS_ARB) == GL11.GL_FALSE) {
            System.err.println(getLogInfo(programId));
            return;
        }

        glBindAttribLocation(programId,0,"a_Position");
        glBindAttribLocation(programId,1,"a_Color");
        glBindAttribLocation(programId,2,"a_TexCoord");

        ARBShaderObjects.glValidateProgramARB(programId);

        if (ARBShaderObjects.glGetObjectParameteriARB(programId,ARBShaderObjects.GL_OBJECT_VALIDATE_STATUS_ARB) == GL11.GL_FALSE) {
            System.err.println(getLogInfo(programId));
            return;
        }

        texID = loadPNGTexture("graphics/uv1.png",GL_TEXTURE0);
        createTexQuad(0,0,32,32);

        exitOnGLError("init");
    }

    public void clearScreen() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render() {
        glUseProgram(programId);

        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D,texID);

        glBindVertexArray(vaoID);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboiID);
        glDrawElements(GL_TRIANGLES,indiceCount,GL_UNSIGNED_BYTE,0);

        glBindBuffer(GL_ARRAY_BUFFER,0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);
        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
        glBindVertexArray(0);

        glUseProgram(0);

        exitOnGLError("render");

    }

    public void show() {
        screen.update();
    }

    private static String getLogInfo(int obj) {
        return ARBShaderObjects.glGetInfoLogARB(obj,ARBShaderObjects.glGetObjectParameteriARB(obj,ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
    }

    private int loadPNGTexture(String filename, int textureUnit) {
        ByteBuffer buf = null;
        int tWidth = 0;
        int tHeight = 0;

        try {
            InputStream in = new FileInputStream(filename);

            PNGDecoder decoder = new PNGDecoder(in);

            tWidth = decoder.getWidth();
            tHeight = decoder.getHeight();
            buf = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
            decoder.decode(buf,4*decoder.getWidth(), PNGDecoder.Format.RGBA);
            if (!decoder.hasAlpha()) {
                System.out.println("Wrong PNG format. Missing alpha channel");
            }
            buf.flip();
            in.close();
        } catch (IOException e) {
            System.err.println("Could not load textures. Doing a totally overkill app crash now ;)");
            e.printStackTrace();
            System.exit(-1);
        }

        int texId = GL11.glGenTextures();
        glActiveTexture(textureUnit);
        glBindTexture(GL_TEXTURE_2D, texId);
        glPixelStorei(GL_UNPACK_ALIGNMENT,1);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, tWidth, tHeight, 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
        glGenerateMipmap(GL_TEXTURE_2D);

        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_S,GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_WRAP_T,GL_REPEAT);

        glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_NEAREST);
        glTexParameterf(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR_MIPMAP_LINEAR);

        return texId;

    }

    public void createTexQuad(int x, int y, int width, int height) {
        TexturedVertex v0 = new TexturedVertex();
        v0.setXYZ(-0.5f,0.5f,-1f); v0.setRGB(1f, 0f, 0f); v0.setST(0,0);
        TexturedVertex v1 = new TexturedVertex();
        v1.setXYZ(-0.5f,-0.5f,-1f); v1.setRGB(0f,1f,0f); v1.setST(0,1);
        TexturedVertex v2 = new TexturedVertex();
        v2.setXYZ(0.5f,-0.5f,-1f); v2.setRGB(0f,0f,1f); v2.setST(1,1);
        TexturedVertex v3 = new TexturedVertex();
        v3.setXYZ(0.5f,0.5f,-1f); v3.setRGB(1f,1f,1f); v3.setST(1,0);
        TexturedVertex vertices[] = new TexturedVertex[] {v0,v1,v2,v3};

        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length*TexturedVertex.elementCount);
        for (int i = 0; i < vertices.length; i++) {
            vertexBuffer.put((vertices[i].getElements()));
        }
        vertexBuffer.flip();

        byte[] indices = {
            0,1,2,
            2,3,0
        };
        indiceCount = indices.length;
        ByteBuffer indiceBuffer = BufferUtils.createByteBuffer(indices.length);
        indiceBuffer.put(indices);
        indiceBuffer.flip();

        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER,vboID);
        glBufferData(GL_ARRAY_BUFFER,vertexBuffer,GL_STATIC_DRAW);

        glVertexAttribPointer(0, TexturedVertex.positionElementCount, GL_FLOAT, false, TexturedVertex.stride, TexturedVertex.positionByteCount);
        glVertexAttribPointer(1, TexturedVertex.colorElementCount, GL_FLOAT, false, TexturedVertex.stride, TexturedVertex.colorByteCount);
        glVertexAttribPointer(2,TexturedVertex.textureElementCount,GL_FLOAT,false,TexturedVertex.stride,TexturedVertex.textureByteCount);

        glBindBuffer(GL_ARRAY_BUFFER,0);

        glBindVertexArray(0);

        vboiID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,vboiID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER,vboiID,GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,0);

        System.out.println("Vao: " + vaoID);
        System.out.println("Vbo: " + vboID);
        System.out.println("Vaoi: " + vboiID);
        System.out.println("Indices: " + indiceCount);
        System.out.println("TexID: " + texID);


    }

    private void exitOnGLError(String errorMessage) {
        int errorValue = glGetError();

        if (errorValue != GL_NO_ERROR) {
            System.err.println("ERROR in" + errorMessage);

            if (Display.isCreated()) {
                Display.destroy();
                System.exit(-1);
            }

        }
    }
}
