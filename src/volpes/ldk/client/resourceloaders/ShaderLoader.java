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

package volpes.ldk.client.resourceloaders;

import org.lwjgl.opengl.ARBFragmentShader;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import volpes.ldk.client.ResourceLoader;
import volpes.ldk.client.opengl.Shader;
import volpes.ldk.utils.Parsers;
import volpes.ldk.utils.VFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lasse Dissing Hansen
 */
public class ShaderLoader implements ResourceLoader {

    private Map<String,Shader> shaderMap = new HashMap<String,Shader>();
    private int numberOfLoadedObjects = 0;

    @Override
    public void initialize() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void shutdown() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object get(String id) {
        return shaderMap.get(id);
    }

    @Override
    public void load(Element xmlElement) {
        String id = xmlElement.getAttribute("id");
        String path = xmlElement.getTextContent();
        if (path == null || path.length() == 0) {
            System.err.println("Shader [" + id + "] has invalid path");
            return;
        }
        StringBuilder src = new StringBuilder();
        InputStreamReader in = null;
        BufferedReader reader = null;
        try {
            in = new InputStreamReader(VFS.getFile(path));
            reader = new BufferedReader(in);
            String line;
            while ((line = reader.readLine()) != null) {
                src.append(line).append('\n');
            }
            reader.close();
            in.close();
        } catch (Exception e) {
            System.err.println("Unable to open file " + id + " at " + path);
            return;
        }

        int type;
        if (xmlElement.getAttribute("type").equalsIgnoreCase("fragment")) {
            type = ARBFragmentShader.GL_FRAGMENT_SHADER_ARB;
        } else if (xmlElement.getAttribute("type").equalsIgnoreCase("vertex")) {
            type = ARBVertexShader.GL_VERTEX_SHADER_ARB;
        } else {
            System.err.println("Shader must either be a fragment or vertex");
            return;
        }

        Shader shader = new Shader(src.toString(),type);

        numberOfLoadedObjects++;
        shaderMap.put(id,shader);


    }

    @Override
    public String getLoaderID() {
        return "shader";
    }

    @Override
    public int getNumberOfLoadedObjects() {
        return numberOfLoadedObjects;
    }
}
