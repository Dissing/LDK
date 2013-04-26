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

package volpes.ldk.client.resources.resourceloaders;

import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.openal.SoundStore;
import org.w3c.dom.Element;
import volpes.ldk.client.audio.ALMusic;
import volpes.ldk.client.audio.Music;
import volpes.ldk.client.resources.ResourceLoader;
import volpes.ldk.utils.LDKException;
import volpes.ldk.utils.VFS;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lasse Dissing Hansen
 */
public class MusicLoader implements ResourceLoader {

    private Map<String,Music>  musicMap = new HashMap<String,Music>();
    private int numberOfLoadedObjects = 0;

    @Override
    public void initialize() {
        /*
        !!!WARNING!!!
        Strange reflective memory hack follows
        Because of my limited time before LD26 I use Slick for loading OGG files.
        The hack hinders Slick in creating its own AL context, (One on can exist!)
        and forces it to use the existing one.
        I should REALLY write my own OGG loading modules at some point...
         */
        try {
            Field inited = AudioLoader.class.getDeclaredField("inited");
            inited.setAccessible(true);
            inited.setBoolean(null, true);
            Field soundWorks = SoundStore.class.getDeclaredField("soundWorks");
            soundWorks.setAccessible(true);
            soundWorks.setBoolean(SoundStore.get(),true);
            Field inited2 = SoundStore.class.getDeclaredField("inited");
            inited2.setAccessible(true);
            inited2.setBoolean(SoundStore.get(), true);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("My terrible hack has failed!");
            System.exit(0);
        }

    }

    @Override
    public void shutdown() {
        for (Music music : musicMap.values())
            music.dispose();
    }

    @Override
    public Object get(String id) {
        return musicMap.get(id);
    }

    @Override
    public void load(Element xmlElement) {
        String id = xmlElement.getAttribute("id");
        String format = xmlElement.getAttribute("format");
        String path = xmlElement.getTextContent();
        if (path == null || path.length() == 0)
            System.err.println("Sound resource [" + id + "] has invalid path");

        Music music;
        try {
            InputStream is = VFS.getFile(path);
            int buffer;
            if (AL10.alGetError() != AL10.AL_NO_ERROR)
                throw new LDKException("OpenAL is probably out of memory. Otherwise I wish you good luck!");

            if (format.equalsIgnoreCase("wav") || format.equalsIgnoreCase("wave")) {
                buffer = AL10.alGenBuffers();
                WaveData waveFile =  WaveData.create(is);
                AL10.alBufferData(buffer,waveFile.format,waveFile.data,waveFile.samplerate);
                waveFile.dispose();
            } else if (format.equalsIgnoreCase("ogg") || format.equalsIgnoreCase("vorbis")) {
                buffer = AudioLoader.getAudio("OGG",is).getBufferID();
            } else {
                throw new LDKException("The audio format " + format + " is not supported by this library!");
            }
            music = new ALMusic(buffer);

        } catch (IOException e) {
            System.err.println("Unable to open file " + id + " at " + path);
            return;
        }
        numberOfLoadedObjects++;
        musicMap.put(id, music);
    }

    @Override
    public String getLoaderID() {
        return "music";
    }

    @Override
    public int getNumberOfLoadedObjects() {
        return numberOfLoadedObjects;
    }
}
