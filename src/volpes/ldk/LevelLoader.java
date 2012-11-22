//============================================================================
// Name        : ResourceManager
// Author      : Lasse Dissing Hansen
// Version     : 0.1
// Copyright   : (C) 2012 Lasse Dissing Hansen
// Description : 
//============================================================================
package volpes.ldk;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class LevelLoader {
	private static Map<String, TileLevel> levelMap;
	
	public static TileLevel loadLevel(InputStream is) throws LDKException{
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new LDKException("Could not load resources",e);
		}
		Document doc = null;
		try {
			doc = docBuilder.parse(is);
		} catch(SAXException e) {
			throw new LDKException("Could not load resources", e);
		} catch(IOException e) {
			throw new LDKException("Could not load resources", e);
		}
		
		doc.getDocumentElement().normalize();
		
		//Get map info
		Element infoElement = (Element)doc.getFirstChild();
		int width = Integer.parseInt(infoElement.getAttribute("width"));
		int height = Integer.parseInt(infoElement.getAttribute("height"));
		
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
		
		TileLevel level = new TileLevel(width,height);
		
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
