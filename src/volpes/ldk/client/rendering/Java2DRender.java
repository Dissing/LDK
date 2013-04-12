package volpes.ldk.client.rendering;

import volpes.ldk.utils.LDKException;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * @author Lasse Dissing Hansen
 */
public class Java2DRender extends AbstractRender {

    Canvas canvas;
    BufferStrategy bs;
    Graphics2D g;

    private int width;
    private int height;

    @Override
    public void initialise(Screen screen) {
        if (!(screen instanceof AWTWindow)) {
            throw new LDKException("Java2D render does not support the following window type" + screen.getClass().getName());
        }
        AWTWindow window = (AWTWindow)screen;

        canvas = window.getCanvas();
        width = window.getWidth();
        height = window.getHeight();
        canvas.createBufferStrategy(3);

    }

    @Override
    public void shutdown() {
        bs.dispose();
    }

    @Override
    public void preRender() {
        bs = canvas.getBufferStrategy();
        g = (Graphics2D)bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,width,height);

    }

    @Override
    public void render() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void postRender() {
        g.dispose();
        bs.show();
        Toolkit.getDefaultToolkit().sync();
    }

    public Graphics2D getGraphics() {
        return g;
    }
}
