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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Lasse Dissing Hansen
 * @version 0.1
 */
public class LevelLoader {
	private static Map<String, TileLevel> levelMap;
	
	public static TileLevel loadLevel(int id,InputStream is) throws LDKException{
		Document doc = ResourceInstance.interpretXML(is);
		//Get map info
		Element infoElement = (Element)doc.getFirstChild();
		int width = Integer.parseInt(infoElement.getAttribute("width"));
		int height = Integer.parseInt(infoElement.getAttribute("height"));
		int tilesize = Integer.parseInt(infoElement.getAttribute("tilewidth"));
		
		//Load tilesets
		NodeList tilesetNodes = doc.getElementsByTagName("tileset");
		List<BufferedImage> tilesetImages = new ArrayList<BufferedImage>();
		for (int i = 0; i < tilesetNodes.getLength(); i++) {
			Node tilesetNode = tilesetNodes.item(i);
			Element imageNode = (Element)tilesetNode.getChildNodes().item(1);
			String path = imageNode.getAttribute("source");
			path = path.substring(2, path.length());
			tilesetImages.add(loadTileSheet(path));
		}
		
		TileLevel level = new TileLevel(id,width,height,tilesize);
		
		//Load layers
		NodeList layerNodes = doc.getElementsByTagName("layer");
		for (int i = 0; i < layerNodes.getLength();i++) {
			Node layerNode = layerNodes.item(i);
			NodeList tileNodes = layerNode.getChildNodes().item(1).getChildNodes(); //Jumps over <data> tag
			level.addLayer(loadLayer(tileNodes),tilesetImages.get(i)); 
		}
		return level;
	}
	
	private static BufferedImage loadTileSheet(String path) {
		try {
			BufferedImage sheet = ImageIO.read(LevelLoader.class.getResource(path));
			return ImageOptimizer.optimize(sheet);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
		
	
	
	private static List<Integer> loadLayer(NodeList tileNodes) throws LDKException{
		List<Integer> tiles = new ArrayList<Integer>();
		for (int index = 0; index < tileNodes.getLength(); index++) {
			
			Node tileNode = tileNodes.item(index);
			
			if (tileNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element)tileNode;
				if (element.getTagName().equalsIgnoreCase("tile")) {
					tiles.add(Integer.parseInt(element.getAttribute("gid")));
				}
			}
		}
		return tiles;
	}
	
	public static final TileLevel getLevel(String ID) {
		return levelMap.get(ID);
	}
	
	
}
