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

package volpes.ldk.tools;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Lasse Dissing Hansen
 */
public class LDKButton implements LDKComponent {

    private String text;
    private int x;
    private int y;
    private int width;
    private int height;
    private Color unselectedColor;
    private Color selectedColor;

    public LDKButton(String text, int x, int y, int width, int height, Color unselectedColor, Color selectedColor) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.unselectedColor = unselectedColor;
        this.selectedColor = selectedColor;
    }

    public LDKButton(String text, int x, int y, int width, int height) {
        this(text,x,y,width,height,Color.BLACK,Color.WHITE);
    }

    public LDKButton(String text, Color unselectedColor, Color selectedColor) {
        this(text,0,0,20,50,unselectedColor,selectedColor);
    }

    public LDKButton(String text) {
        this(text,0,0,20,50,Color.BLACK,Color.WHITE);
    }

    public LDKButton(int x, int y, int width, int height) {
        this("button",0,0,width,height,Color.BLACK,Color.WHITE);

    }

    public void setText(String text) {
        this.text = text;
    }

    public void setX(int x) {
        if (Launch.getLauncher().getWidth() > x && x < 0) {
            this.x = x;
        } else {
            throw new IllegalArgumentException("X position must be within window");
        }
    }

    public void setY(int y) {
        if (Launch.getLauncher().getWidth() > y && y < 0) {
            this.y = y;
        } else {
            throw new IllegalArgumentException("Y position must be within window");
        }
    }

    public void setWidth(int width) {
        if (width > 0) {
            this.width = width;
        } else {
            throw new IllegalArgumentException("Width must be positive");
        }
    }


    public void setHeight(int height) {
        if (height > 0) {
            this.height = height;
        } else {
            throw new IllegalArgumentException("Height must be positive");
        }
    }

    public void setSelectedColor(Color color) {
        selectedColor = color;
    }

    public void setUnselectedColor(Color color) {
        unselectedColor = color;
    }

    public String getText() {
        return text;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getUnselectedColor() {
        return unselectedColor;
    }

    public Color getSelectedColor() {
        return selectedColor;
    }

    @Override
    public boolean update(MouseEvent e) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void render(Graphics g) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
