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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Lasse Dissing Hansen
 */
public class EventManager {

    public static final int ID_QUERY = 0;
    public static final int ATTRIBUTE_QUERY = 1;
    public static final int RADIUS_QUERY = 2;

    private final GameContainer container;

    public EventManager(GameContainer container) {
        this.container = container;
    }

    public List<Entity> queryObjects(int type, Object caller) {
        ArrayList<Entity> list = new ArrayList<Entity>();
        Iterator iter = container.getWorld().iterator();
        while (iter.hasNext()) {
            Entity entity = (Entity)iter.next();
            if (entity.query(type,caller)) {
                list.add(entity);
            }
        }

        return list;
    }

    public void sendEvent(Event event) {
        Iterator iter = container.getWorld().iterator();
        while (iter.hasNext()) {
            ((Entity)iter.next()).gotEvent(event);
        }

    }


}
