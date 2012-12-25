/*
 * Copyright (C) 2012 Lasse Dissing Hansen
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

package volpes.ldk;

import org.w3c.dom.Element;

/**
 * @author Lasse Dissing Hansen
 */
public interface ResourceLoader {

    /**
     * Called right after being attached to the manager
     */
    public void initialize();


    //TODO Actually call this somewhere
    public void shutdown();

    /**
     * Returns the resource with the given id
     * @param id The id of the resource
     * @return The resource
     */
    public Object get(String id);

    /**
     * Loads a new resource
     * Only to be called by the manager
     * @param xmlElement The XML element from the resource file
     */
    public void load(Element xmlElement);

    /**
     * Returns the loaderID also known as the resource type
     * @return The loaderID
     */
    public String getLoaderID();

    /**
     * Returns the number of resources currently loaded by this loader
     * @return The total amount of resources loaded
     */
    public int getNumberOfLoadedObjects();
}