package volpes.ldk.client.audio;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.OpenALException;

/**
 * @author Lasse Dissing Hansen
 */
public class ALMusic implements Music {

    int source;

    int buffer;

    boolean playing = false;

    boolean looping = false;

    public ALMusic(int  buffer) {
        this.buffer = buffer;
        source = AL10.alGenSources();

        if (AL10.alGetError() != AL10.AL_NO_ERROR)
            throw new OpenALException("Unable to allocate source!");

        AL10.alSourcei(source,AL10.AL_BUFFER,buffer);
        AL10.alSourcef(source,AL10.AL_PITCH,1.0f);
        AL10.alSourcef(source,AL10.AL_GAIN,1.0f);
        AL10.alSourcei(source,AL10.AL_LOOPING,AL10.AL_FALSE);
    }

    @Override
    public void play() {
        AL10.alSourcePlay(source);
        playing = true;
    }

    @Override
    public void stop() {
        AL10.alSourceStop(source);
        playing = false;
    }

    @Override
    public void pause() {
        AL10.alSourcePause(source);
        playing = false;
    }

    @Override
    public void setLooping(boolean looping) {
        AL10.alSourcei(source,AL10.AL_LOOPING,looping ? AL10.AL_TRUE : AL10.AL_FALSE);
        this.looping = looping;
    }

    @Override
    public void setVolume(float volume) {
        AL10.alSourcei(source,AL10.AL_GAIN,AL10.AL_FALSE);
    }

    @Override
    public boolean isPlaying() {
        return playing;
    }

    @Override
    public boolean isLooping() {
        return looping;
    }

    @Override
    public void dispose() {
        AL10.alDeleteSources(source);
        AL10.alDeleteBuffers(buffer);
    }
}
