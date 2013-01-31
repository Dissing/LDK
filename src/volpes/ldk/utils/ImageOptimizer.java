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
package volpes.ldk.utils;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;

/**
 * Optimises the image to the native colour model
 * Yet to find a device where this matters, but who knows...
 * @author Lasse Dissing Hansen
 * @since 0.1
 */
public class ImageOptimizer {
	public static BufferedImage optimize(BufferedImage image) {
		GraphicsConfiguration graphicsConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		
		if (image.getColorModel().equals(graphicsConfig.getColorModel())) 
			return image;
		
		BufferedImage newImage = graphicsConfig.createCompatibleImage(image.getWidth(), image.getHeight(),image.getTransparency());
		
		Graphics g2d = newImage.getGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		return newImage;
			
	}
}
