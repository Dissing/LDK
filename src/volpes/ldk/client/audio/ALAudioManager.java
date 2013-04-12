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

        ALSound.manager = this;
        ALMusic.manager = this;

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
