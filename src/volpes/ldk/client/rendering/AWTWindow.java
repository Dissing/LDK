package volpes.ldk.client.rendering;

import volpes.ldk.client.input.Input;

import javax.swing.*;
import java.awt.*;

/**
 * @author Lasse Dissing Hansen
 */
public class AWTWindow extends Screen {

    JFrame frame;
    JPanel panel;
    Canvas canvas;

    Input input;


    @Override
    public void createScreen() {

        frame = new JFrame("LDK");
        //frame.setUndecorated(true);
        panel = new JPanel(new BorderLayout());
        canvas = new Canvas();
        panel.add(canvas);

        frame.setContentPane(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640,480);
        frame.setVisible(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int w = (int)screenSize.getWidth();
        int h = (int)screenSize.getHeight();
        frame.setLocation(w/2-640,h/2-360);

        input = new Input();
        canvas.addMouseListener(input);
        canvas.addMouseMotionListener(input);
        canvas.addKeyListener(input);
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

    public Input getInput() {
        return input;
    }


}
