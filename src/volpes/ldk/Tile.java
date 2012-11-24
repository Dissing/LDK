/*Copyright (C) 2012 Lasse Dissing Hansen

Permission is hereby granted, free of charge, to any person obtaining a copy of 
this software and associated documentation files (the "Software"), to deal in the Software without restriction, 
including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package volpes.ldk;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lasse Dissing Hansen
 * @version 0.1
 */
public class Tile {
	
	private BufferedImage image;
	private int id;
	private Map<String,String> properties = new HashMap<String,String>();
	
	public Tile(BufferedImage image, int id) {
		this.image = image;
		this.id = id;
	}
	
	protected void addProperties(Map<String,String> properties) {
		this.properties = properties;
	}
	
	public boolean getPropertyAsBool(String key) {
		if (properties.containsKey(key)) {
			return Boolean.parseBoolean(properties.get(key));
		} else {
			return false;
		}
	}
	
	public int getPropertyAsInt(String key) {
		if (properties.containsKey(key)) {
			return Integer.parseInt(properties.get(key));
		} else {
			return 0;
		}
	}
	
	public String getPropertyAsString(String key) {
		if (properties.containsKey(key)) {
			return properties.get(key);
		} else {
			return null;
		}
	}

	
	public BufferedImage getImage() {
		return image;
	}
	
	public int getID() {
		return id;
	}
}
