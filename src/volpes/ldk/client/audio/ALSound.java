package volpes.ldk.client.audio;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.OpenALException;

/**
 * @author Lasse Dissing Hansen
 */
public class ALSound implements Sound {

    public int buffer;

    public int source;

    public ALSound(int buffer) {
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
    public void play(float volume) {
        if (volume > 1.0f) volume = 1.0f;
        if (volume < 0.0f) volume = 0.0f;
        AL10.alSourcef(source,AL10.AL_GAIN,volume);
        AL10.alSourcePlay(source);
    }

    @Override
    public void dispose() {
        AL10.alDeleteSources(source);
        AL10.alDeleteBuffers(buffer);
    }


}
