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

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.GL11;
import volpes.ldk.client.LDKException;

/**
 * @author Lasse Dissing Hansen
 */
public class Shader {

    private int id;
    private int type;
    private String src;

    public Shader(String src, int type) {
        if (type == ARBVertexShader.GL_VERTEX_SHADER_ARB || type == ARBFragmentShader.GL_FRAGMENT_SHADER_ARB) {
            id = ARBShaderObjects.glCreateShaderObjectARB(type);

            if (id == 0) {
                throw new LDKException("Unable to allocate shader. Something fishy going on. Glad im not to debug this");
            }
            ARBShaderObjects.glShaderSourceARB(id,src);
            ARBShaderObjects.glCompileShaderARB(id);
            if (ARBShaderObjects.glGetObjectParameteriARB(id,ARBShaderObjects.GL_OBJECT_COMPILE_STATUS_ARB) == GL11.GL_FALSE) {
                String log = ARBShaderObjects.glGetInfoLogARB(id,ARBShaderObjects.glGetObjectParameteriARB(id,ARBShaderObjects.GL_OBJECT_INFO_LOG_LENGTH_ARB));
                throw new LDKException("Unable to compile shader " + log);
            }

        }

    }


}
