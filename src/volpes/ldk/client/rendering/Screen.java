package volpes.ldk.client.rendering;

import java.awt.*;

/**
 * @author Lasse Dissing Hansen
 */
public abstract class Screen implements ScreenManager {


    public abstract void createScreen();

    public abstract void destroyScreen();

    public abstract boolean isCloseRequested();

}
