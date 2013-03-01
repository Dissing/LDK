package volpes.ldk.tools;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.util.ArrayList;

/**
 * @author Lasse Dissing Hansen
 */
public class Launch implements MouseMotionListener{

    private static Launch launcher;

    JFrame frame;
    JPanel panel;
    Canvas canvas;

    int x;
    int y;

    int width;
    int height;

    private ArrayList<Gui> guiArray = new ArrayList<Gui>();
    private int currentGui;

    private boolean running;

    private Launch() {

        launcher = this;

        GraphicsDevice screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        x = (int)(screen.getDisplayMode().getWidth() * 0.35);
        y = (int)(screen.getDisplayMode().getHeight() * 0.2);

        width = (int)(screen.getDisplayMode().getWidth() * 0.3);
        height = (int)(width * 1.1);

        frame = new JFrame();
        panel = new JPanel();
        canvas = new Canvas();
        panel.add(canvas);

        frame.setUndecorated(true);
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocation(x, y);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }



    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        if (guiArray.get(currentGui).update(e)) {
            guiArray.get(currentGui).render(canvas.getGraphics());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //Not needed but required by the MouseMovementListener interface
    }

    public static Launch getLauncher() {
        return launcher;
    }

    public static void main(String args[]) {
        Launch launch = new Launch();

    }

}
