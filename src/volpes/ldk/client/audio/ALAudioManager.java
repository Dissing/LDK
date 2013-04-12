package volpes.ldk.client.audio;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import volpes.ldk.utils.LDKException;

/**
 * @author Lasse Dissing Hansen
 */
public class ALAudioManager implements AudioManager {


    private float masterVolume = 1.0f;

    private float soundVolume = 1.0f;

    private float musicVolume = 1.0f;

    private boolean soundEnabled = true;

    private boolean musicEnabled = true;

    public void initialise() {
        try {
            AL.create();
        } catch (LWJGLException e) {
            throw new LDKException("Unable to create OpenAL context", e);
        }

    }

    public void shutdown() {
        AL.destroy();
    }

    @Override
    public void setMasterVolume(float volume) {
        if (volume > 1.0f) volume = 1.0f;
        if (volume < 0.0f) volume = 0.0f;
        masterVolume = volume;
    }

    @Override
    public void setSoundVolume(float volume) {
        if (volume > 1.0f) volume = 1.0f;
        if (volume < 0.0f) volume = 0.0f;
        soundVolume = volume;
    }

    @Override
    public void setMusicVolume(float volume) {
        if (volume > 1.0f) volume = 1.0f;
        if (volume < 0.0f) volume = 0.0f;
        musicVolume = volume;
    }

    @Override
    public void muteSound(boolean mute) {
        soundEnabled = !mute;
    }

    @Override
    public void muteMusic(boolean mute) {
        musicEnabled = !mute;
    }

    @Override
    public float getMasterVolume() {
        return masterVolume;
    }

    @Override
    public float getSoundVolume() {
        return soundVolume;
    }

    @Override
    public float getMusicVolume() {
        return musicVolume;
    }

    @Override
    public boolean isSoundMuted() {
        return !soundEnabled;
    }

    @Override
    public boolean isMusicMuted() {
        return !musicEnabled;
    }
}
