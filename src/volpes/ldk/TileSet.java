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

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lasse Dissing Hansen
 */
public class TileSet {

    private String name;
    private int tileWidth;
    private int tileHeight;
    private int spacing;
    private int margin;

    private int width;
    private int height;

    private BufferedImage source;

    private List<BufferedImage> tiles = new ArrayList<BufferedImage>();

    TileSet(String name, BufferedImage source, int tileWidth, int tileHeight, int spacing, int margin) {
        this.name = name;
        this.source = source;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.spacing = spacing;
        this.margin = spacing;
        width = source.getWidth() / tileWidth;
        height = source.getHeight() / tileHeight;
        for (int j = 0; j < height; j++) {
             for (int i = 0; i < width; i++) {
                 tiles.add(source.getSubimage(i*tileWidth,j*tileHeight,tileWidth,tileHeight));
            }
        }
    }


    /**
     * Returns the tile image of the index
     * @param index The index of the tile image
     * @return The tile image
     */
    public BufferedImage get(int index) {
        return tiles.get(index-1);
    }

    /**
     * Returns the name of the tile set
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the width of each tile
     * @return The tile width
     */
    public int getTileWidth() {
        return tileWidth;
    }

    /**
     * Returns the height of each tile
     * @return The tile height
     */
    public int getTileHeight() {
        return tileHeight;
    }

    /**
     * Returns the number of pixels between each tile
     * @return The spacing
     */
    public int getSpacing() {
        return spacing;
    }

    /**
     * Returns the number of pixels margin the tile set
     * @return The margin
     */
    public int getMargin() {
        return margin;
    }

    /**
     * Returns the number of tiles spanning on the x-axis
     * @return Tiles along the x-axis
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the number of tiles spanning on the y-axis
     * @return Tiles along the y-axis
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the whole sprite sheet in one image
     * @return The sprite sheet
     */
    public BufferedImage getSource() {
        return source;
    }

}
