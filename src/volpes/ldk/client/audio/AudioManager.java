package volpes.ldk.client.audio;

/**
 * @author Lasse Dissing Hansen
 */
public interface AudioManager {

    public void setMasterVolume(float volume);

    public void setSoundVolume(float volume);

    public void setMusicVolume(float volume);

    public void muteSound(boolean mute);

    public void muteMusic(boolean mute);

    public float getMasterVolume();

    public float getSoundVolume();

    public float getMusicVolume();

    public boolean isSoundMuted();

    public boolean isMusicMuted();
}
