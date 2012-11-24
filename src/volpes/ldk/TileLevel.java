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

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lasse Dissing Hansen
 * @version 0.1
 */
public class TileLevel {
	
	private int id;
	
	private TileManager manager = new TileManager();
	
	private int width,height;

	private List<Layer> layers = new ArrayList<Layer>();
	
	private int tileSize = manager.getSize();
	
	/**
	 * Creates a new TileLevel with a specified size
	 * @param width
	 * @param height
	 */
	public TileLevel(int id, int width, int height) {
		this.width = width;
		this.height = height;
		this.id = id;
	}
	
	/**
	 * Returns the tile at specified coordinate
	 * It is map coordinate and not screen coordinate
	 * @param x The X map coordinate of the tile
	 * @param y The Y map coordinate of the tile 
	 * @param layer The layer index
	 * @return The tile
	 */
	public Tile getTile(int x, int y, int layer) {
		try {
			return manager.getType(layers.get(layer).get(x + y * width),layer);
		} catch (LDKException e) {
			return manager.getDefault();
		}
	}
	
	/**
	 * Renders the map
	 * @param container The game container
	 * @param g The Graphics for drawing the map
	 */
	public void render(GameContainer container, Graphics2D g) {
		for (int layer = 0; layer < layers.size(); layer++) {
			if (!layers.get(layer).cache) {
				drawLayer(g,layer);
			} else {
				g.drawImage(layers.get(layer).cachedImage, 0, 0, null);
			}
		}
	}
	
	/**
	 * Draws a single layer to a Graphics
	 * @param g The Graphics to draw to
	 * @param layer The layer to render
	 */
	private void drawLayer(Graphics2D g,int layer) {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Tile tile = getTile(i,j,layer);
				if (tile != null) {
					g.drawImage(tile.getImage(), i*tileSize, j*tileSize, null);
				}
			}
		}
	}
	
	/**
	 * Adds a layer to the rendering process
	 * @param layer
	 * @param tileSheet
	 */
	public void addLayer(List<Integer> layer,BufferedImage tileSheet) {
		layers.add(new Layer(layer));
		manager.addTileSheet(tileSheet);
	}
	
	/**
	 * Caches a layer to a BufferedImage for optimised drawing
	 * The background can not change will being cached
	 * To disable caching again set cache to false
	 * @param cache 
	 * @param index
	 */
	public void cacheLayer(boolean cache, int index) {
		if (cache) {
			GraphicsConfiguration graphicsConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
			layers.get(index).cache = true;
			layers.get(index).cachedImage = graphicsConfig.createCompatibleImage(width*manager.getSize(), height*manager.getSize());
			Graphics2D g2d = (Graphics2D) layers.get(index).cachedImage.getGraphics();
			drawLayer(g2d,index);
			g2d.dispose();
		} else {
			layers.get(index).cache = false;
			layers.get(index).cachedImage = null;
		}
	}
	
	/**
	 * @return The level id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Private data structure for internal representation of layer
	 */
	private class Layer {
		public boolean cache = false;
		public BufferedImage cachedImage;
		public List<Integer> tiles;
		public Layer(List<Integer> layer) {
			this.tiles = layer;
		}
		
		public int get(int index) {
			return tiles.get(index);
		}
	}
}
