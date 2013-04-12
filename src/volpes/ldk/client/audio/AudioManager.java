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

/**
 * @author Lasse Dissing Hansen
 */
public interface AudioManager {

    /**
     * Sets the master volume for all audio
     * @param volume Value between 0.0f and 1.0f
     */
    public void setMasterVolume(float volume);

    /**
     * Sets the volume for all {@link Sound}s
     * @param volume Value between 0.0f and 1.0f
     */
    public void setSoundVolume(float volume);

    /**
     * Sets the volume for all {@link Music}
     * @param volume Value between 0.0f and 1.0f
     */
    public void setMusicVolume(float volume);

    /**
     * Mutes all {@link Sound}
     * @param mute True for no sound
     */
    public void muteSound(boolean mute);

    /**
     * Mutes all {@link Music}
     * @param mute True for no music
     */
    public void muteMusic(boolean mute);

    /**
     * Returns the master volume for all audio
     * @return Value between 0.0f and 1.0f
     */
    public float getMasterVolume();

    /**
     * Returns the volume for all {@link Sound}
     * @return Value between 0.0f and 1.0f
     */
    public float getSoundVolume();

    /**
     * Returns the volume for all {@link Music}
     * @return Value between 0.0f and 1.0f
     */
    public float getMusicVolume();

    /**
     * Returns whether the {@link Sound} is muted or not
     * @return True for no sound
     */
    public boolean isSoundMuted();

    /**
     * Returns whether the {@link Music} is muted or not
     * @return True for no music
     */
    public boolean isMusicMuted();
}
