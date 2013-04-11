package volpes.ldk.client.rendering;

import javax.swing.*;
import java.awt.*;

/**
 * @author Lasse Dissing Hansen
 */
public class AWTWindow extends Screen {

    JFrame frame;
    JPanel panel;
    Canvas canvas;


    @Override
    public void createScreen() {

        frame = new JFrame();
        panel = new JPanel(new BorderLayout());
        canvas = new Canvas();
        panel.add(canvas);

        frame.setContentPane(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void destroyScreen() {
        frame.dispose();
    }

    @Override
    public boolean isCloseRequested() {
        return false;
    }

    @Override
    public void setTitle(String title) {
        frame.setTitle(title);
    }

    @Override
    public void setSize(int x, int y) {
        frame.setSize(x,y);
    }

    @Override
    public void setSize(Dimension dimension) {
        frame.setSize(dimension);
    }

    @Override
    public void setLocation(int x, int y) {
        frame.setLocation(x,y);
    }

    @Override
    public int getWidth() {
        return frame.getWidth();
    }

    @Override
    public int getHeight() {
        return frame.getHeight();
    }

    @Override
    public float getAspectRatio() {
        return frame.getWidth() / (float) frame.getHeight();
    }

    public Canvas getCanvas() {
        return canvas;
    }

}
