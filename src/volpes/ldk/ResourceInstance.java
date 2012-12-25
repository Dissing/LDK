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
import java.util.HashMap;
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

/**
 * @author Lasse Dissing Hansen
 * @since 0.1
 */
public class ResourceInstance {
	
	private Map<String, AnimationSheet> animationMap;
	private Map<String, String> stringMap;
	private Map<String, BufferedImage> imageMap;
	
	public ResourceInstance(InputStream is) throws LDKException {
		stringMap = new HashMap<String,String>();
		animationMap = new HashMap<String,AnimationSheet>();
		imageMap = new HashMap<String,BufferedImage>();
		Document doc = interpretXML(is);
		loadResources(doc.getElementsByTagName("resource"));
	}
	
	public static Document interpretXML(InputStream is) throws LDKException{
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			throw new LDKException("Could not load resources",e);
		}
		Document doc;
		try {
			doc = docBuilder.parse(is);
			is.close();
		} catch(SAXException e) {
			throw new LDKException("Could not load resources", e);
		} catch(IOException e) {
			throw new LDKException("Could not load resources", e);
		}
		
		doc.getDocumentElement().normalize();
		
		return doc;
	}
	
	
	private void loadResources(NodeList resources) throws LDKException{
		int totalResources = resources.getLength();
		for (int index = 0; index < totalResources; index++) {
			
			Node resourceNode = resources.item(index);
			
			if (resourceNode.getNodeType() == Node.ELEMENT_NODE) {
				Element resourceElement = (Element)resourceNode;
				
				String type = resourceElement.getAttribute("type");
				
				if (type.equals("string")){
					addElementAsString(resourceElement);
				} else if(type.equals("animation")) {
					addElementAsAnimation(resourceElement);
				} else if(type.equals("image")) {
					addElementAsImage(resourceElement);
				}
			}
		}
	}
	
	private final void addElementAsAnimation(Element resourceElement) throws LDKException {
		loadAnimation(resourceElement.getAttribute("id"),resourceElement.getTextContent(),resourceElement.getAttribute("size"));
	}
	
	public AnimationSheet loadAnimation(String id, String path, String tilesize) throws LDKException {
		if (path == null || path.length() == 0) 
			throw new LDKException("Image resource [" + id + "] has invalid path");
			
			BufferedImage image;
			
			try {
				image = ImageIO.read(ResourceInstance.class.getResourceAsStream(path));
			} catch (IOException e) {
				throw new LDKException("Could not load image",e);
			}
			AnimationSheet sheet = new AnimationSheet(image, Integer.parseInt(tilesize));
			
			this.animationMap.put(id, sheet);
			
			return sheet;
	}
	

	public final AnimationSheet getAnimation(String ID) {
		return animationMap.get(ID);
	}
	
	public void addElementAsString(Element ressourceElement) throws LDKException {
		loadString(ressourceElement.getAttribute("id"),ressourceElement.getTextContent());
	}
	
	public String loadString(String id, String string) throws LDKException {
		if (string == null || string.length() == 0)
			throw new LDKException("String [" + id + "] is invalid");
		
		this.stringMap.put(id, string);
		
		return string;
	}
	
	public final String getString(String ID) {
		return stringMap.get(ID);
	}
	
	private final void addElementAsImage(Element resourceElement) throws LDKException {
		loadImage(resourceElement.getAttribute("id"),resourceElement.getTextContent());
	}
	
	public BufferedImage loadImage(String id, String path) throws LDKException {
		if (path == null || path.length() == 0) 
			throw new LDKException("Image resource [" + id + "] has invalid path");
			
			BufferedImage image;
			
			try {
				image = ImageIO.read(ResourceInstance.class.getResourceAsStream(path));
			} catch (IOException e) {
				throw new LDKException("Could not load image",e);
			}
			this.imageMap.put(id, image);
			return image;
	}
	

	public final BufferedImage getImage(String ID) {
		return imageMap.get(ID);
	}
	
}

