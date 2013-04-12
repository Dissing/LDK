package volpes.ldk.client.audio;

/**
 * @author Lasse Dissing Hansen
 */
public interface Music {

    public void play();

    public void stop();

    public void pause();

    public void setLooping(boolean looping);

    public void setVolume(float volume);

    public boolean isPlaying();

    public boolean isLooping();

    public void dispose();
}
