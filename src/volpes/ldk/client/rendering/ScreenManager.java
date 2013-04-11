package volpes.ldk.client.rendering;

import java.awt.*;

/**
 * @author Lasse Dissing Hansen
 */
public interface ScreenManager {

    public void setTitle(String title);

    public void setSize(int x, int y);

    public void setSize(Dimension dimension);

    public void setLocation(int x, int y);

    public int getWidth();

    public int getHeight();

    public float getAspectRatio();

}
