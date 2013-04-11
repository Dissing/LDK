package volpes.ldk.client.rendering;

/**
 * @author Lasse Dissing Hansen
 */
public abstract class AbstractRender {

    public abstract void initialise(Screen screen);

    public abstract void shutdown();

    public abstract void preRender();

    public abstract void render();

    public abstract void postRender();

}
