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
public interface Music {


    /**
     * Plays the music
     * Will start from latest position if music is paused.
     */
    public void play();

    /**
     * Will stop the music and reset location to the start.
     */
    public void stop();

    /**
     * Will pause the music.
     * play() will continue from this location.
     */
    public void pause();

    /**
     * Sets whether the music should be looping
     * @param looping True for looping
     */
    public void setLooping(boolean looping);

    /**
     * Sets the volume
     * @param volume Value between 0.0f and 1.0f
     */
    public void setVolume(float volume);

    /**
     * Returns whether this is currently playing
     * @return True for currently playing
     */
    public boolean isPlaying();

    /**
     * Returns whether this is looping
     * @return True for looping
     */
    public boolean isLooping();

    /**
     * Frees this resource
     * All calls after this results in undefined behavior
     * Should generally not be used.
     */
    public void dispose();
}
