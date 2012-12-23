package volpes.ldk;

import java.awt.image.BufferedImage;
import java.awt.Color;

/**
 * The interface of rendering
 * All drawing to the screen should happen though this
 * @author Lasse Dissing Hansen
 * @since 0.2
 */
public abstract class Render {

    /**
     * Draws as much of the image as possible to the current context
     * @param img The image to draw
     * @param x The x coordinate to start drawing from
     * @param y The y coordinate to start drawing from
     */
    public abstract void drawImage(BufferedImage img,int x, int y);

    /**
     * Draws the string to the current context
     * @param str The String to draw
     * @param x The x coordinate to start drawing from
     * @param y The y coordinate to start drawing from
     */
    public abstract void drawString(String str, int x, int y);

    /**
     * Draws a line from the first coordinate to the second
     * @param x1 The x coordinate of the line start
     * @param y1 The y coordinate of the line start
     * @param x2 The x coordinate of the line end
     * @param y2 The y coordinate of the line end
     */
    public abstract void drawLine(int x1, int y1, int x2, int y2);

    /**
     * Draws the perimeter of a rectangle
     * The inside of the rectangle will be transparent
     * @param x The x coordinate of the rectangles top-left corner
     * @param y The y coordinate of the rectangles top-left corner
     * @param width  The width of the rectangle
     * @param height The height of the rectangle
     */
    public abstract void drawRect(int x, int y, int width, int height);

    /**
     * Draws a rectangle and fills the inside
     * @param x The x coordinate of the rectangles top-left corner
     * @param y The y coordinate of the rectangles top-left corner
     * @param width The width of the rectangle
     * @param height The height of the rectangle
     */
    public abstract void fillRect(int x, int y, int width, int height);

    /**
     * Sets the current color of the graphics context
     * All drawing will happen with this color until a new color is specified
     * @param color The color to use
     */
    public abstract void setColor(Color color);

    /**
     * Applies a scale transformation matrix to the matrix stack
     * [    sx  0   0   ]
     * [    0   sy  0   ]
     * [    0   0   1   ]
     * @param sx The scale factor of the x coordinate
     * @param sy The scale factor of the y coordinate
     */
    public abstract void scale(float sx, float sy);

    /**
     * Applies a translation transformation matrix to the matrix stack
     * [    1   0   tx  ]
     * [    0   1   ty  ]
     * [    0   0   1   ]
     * @param tx The amount to translate on the x axis
     * @param ty The amount to translate on the y axis
     */
    public abstract void translate(int tx, int ty);

    /**
     * Applies a rotation transformation matrix to the matrix stack
     * [    cos(theta), -sin(theta) 0   ]
     * [    sin(theta), cos(theta)  0   ]
     * [    0           0           1   ]
     * @param theta The angle of rotation in radians
     */
    public abstract void rotate(double theta);

    /**
     * Rotates around a given center
     * Corresponds to:
     * translate(x,y);
     * rotate(theta);
     * translate(-x,-y);
     * @param theta The angle of rotation in radians
     * @param x The x coordinate of the origin of the rotation
     * @param y The y coordinate of the origin of the rotation
     */
    public abstract void rotate(double theta, int x, int y);

    /**
     * Pushes a new matrix onto the transformation stack
     */
    public abstract void push();

    /**
     * Pops the current matrix of the transformation stack
     */
    public abstract void pop();

    /**
     * Called every time before rendering a frame
     */
    protected abstract void preRender();

    /**
     * Called every time after a frame
     */
    protected abstract void postRender();

    /**
     * Initialize the screen here
     */
    protected abstract void initScreen();

    /**
     * Called after {@link #initScreen()} but before first frame is rendered
     */
    protected abstract void initRender();

    protected abstract void attachInput(Input input);

    protected abstract void updateSettings(Settings settings);

}
