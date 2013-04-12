/*
 * Copyright (C) 2013 Lasse Dissing Hansen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package volpes.ldk.client.audio;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.OpenALException;

/**
 * @author Lasse Dissing Hansen
 */
public class ALSound implements Sound {

    static ALAudioManager manager;

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
        if (!manager.isSoundMuted()) {
            if (volume > 1.0f) volume = 1.0f;
            if (volume < 0.0f) volume = 0.0f;
            AL10.alSourcef(source,AL10.AL_GAIN,volume*manager.getMasterVolume()*manager.getSoundVolume());
            AL10.alSourcePlay(source);
        }
    }

    @Override
    public void dispose() {
        AL10.alDeleteSources(source);
        AL10.alDeleteBuffers(buffer);
    }


}
