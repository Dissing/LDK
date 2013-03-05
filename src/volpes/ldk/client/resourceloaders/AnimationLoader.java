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

import org.w3c.dom.Element;
import volpes.ldk.client.AnimationSheet;
import volpes.ldk.client.ResourceLoader;
import volpes.ldk.utils.ImageOptimizer;
import volpes.ldk.utils.VFS;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lasse Dissing Hansen
 */
public class AnimationLoader implements ResourceLoader {

    private Map<String,AnimationSheet> animationSheetMap = new HashMap<String,AnimationSheet>();
    private int numberOfLoadedObjects = 0;

    @Override
    public void initialize() {
    }


    @Override
    public void shutdown() {
    }

    @Override
    public Object get(String id) {
        return animationSheetMap.get(id);
    }

    @Override
    public void load(Element xmlElement) {
        String id = xmlElement.getAttribute("id");
        String path = xmlElement.getTextContent();
        String tilesize = xmlElement.getAttribute("size");
        if (path == null || path.length() == 0) {
            System.err.println("Image resource [" + id + "] has invalid path");
            return;
        }
        BufferedImage image;
        try {
            InputStream is = VFS.getFile(path);
            image = ImageIO.read(is);
            image = ImageOptimizer.optimize(image);
        } catch (FileNotFoundException e) {
            System.err.println("Unable to open file " + id + " at " + path);
            return;
        } catch (IOException e) {
            System.err.println("Unable to open file " + id + " at " + path);
            return;
        }

        AnimationSheet sheet = new AnimationSheet(image,Integer.parseInt(tilesize));

        numberOfLoadedObjects++;
        animationSheetMap.put(id,sheet);
    }

    @Override
    public String getLoaderID() {
        return "animation";
    }

    @Override
    public int getNumberOfLoadedObjects() {
        return numberOfLoadedObjects;
    }
}
