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

package volpes.ldk.client.resources;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import volpes.ldk.utils.LDKException;
import volpes.ldk.utils.Parsers;
import volpes.ldk.utils.VFS;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lasse Dissing Hansen
 */
public class ResourceMgr implements ResourceManager{

    private Map<String,ResourceLoader> loaders = new HashMap<String, ResourceLoader>();


    /**
     * Initializes the resource manager and loads ALL resources into memory
     * @param filename The filename of the resource file
     */
    public ResourceMgr(String filename) {
        InputStream is = null;
        try {
            is = VFS.getFile(filename);
        }  catch (FileNotFoundException e) {
            System.err.println("Unable to locate the resource file \""+filename+"\" at the games root directory");
            System.exit(1);
        }
        loadResources(Parsers.parseXML(is));
    }

    public void shutdown() {
        for (ResourceLoader loader : loaders.values()) {
            loader.shutdown();
        }
    }

    private void loadResources(Document doc) {
        NodeList resourceNodes = doc.getElementsByTagName("resource");
        int totalResources = resourceNodes.getLength();

        for (int i = 0; i < totalResources; i++) {
            Node resourceNode = resourceNodes.item(i);

            if (resourceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element resourceElement = (Element)resourceNode;

                String loaderID = resourceElement.getAttribute("type");

                if (loaders.containsKey(loaderID)) {
                    loaders.get(loaderID).load(resourceElement);
                } else {
                    System.err.println("No loader was registered for ID: " + loaderID + " ignoring file");
                }
            }
        }
    }

    public void attachLoader(ResourceLoader loader, String loaderID) {
        loaders.put(loaderID,loader);
        loader.initialize();
    }

    public Object get(String type, String id) {
        if (!loaders.containsKey(type)) {
            throw new LDKException("That resource type is not know by this manager");
        }
        return loaders.get(type).get(id);
    }


}
