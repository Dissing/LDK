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
import org.w3c.dom.Element;
import volpes.ldk.client.audio.ALMusic;
import volpes.ldk.client.audio.ALSound;
import volpes.ldk.client.audio.Music;
import volpes.ldk.client.audio.Sound;
import volpes.ldk.client.resources.ResourceLoader;
import volpes.ldk.utils.LDKException;
import volpes.ldk.utils.VFS;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lasse Dissing Hansen
 */
public class SoundLoader implements ResourceLoader {

    private Map<String,Sound> soundMap = new HashMap<String,Sound>();
    private int numberOfLoadedObjects = 0;

    @Override
    public void initialize() {

    }

    @Override
    public void shutdown() {
        for (Sound sound : soundMap.values())
            sound.dispose();
    }

    @Override
    public Object get(String id) {
        return soundMap.get(id);
    }

    @Override
    public void load(Element xmlElement) {
        String id = xmlElement.getAttribute("id");
        String path = xmlElement.getTextContent();
        if (path == null || path.length() == 0)
            System.err.println("Sound resource [" + id + "] has invalid path");

        Sound sound;
        try {
            InputStream is = VFS.getFile(path);
            WaveData waveFile =  WaveData.create(is);
            int buffer = AL10.alGenBuffers();
            if (AL10.alGetError() != AL10.AL_NO_ERROR)
                throw new LDKException("OpenAL is probably out of memory. Otherwise I wish you good luck!");
            AL10.alBufferData(buffer,waveFile.format,waveFile.data,waveFile.samplerate);
            waveFile.dispose();
            sound = new ALSound(buffer);

        } catch (FileNotFoundException e) {
            System.err.println("Unable to open file " + id + " at " + path);
            return;
        }
        numberOfLoadedObjects++;
        soundMap.put(id, sound);
    }

    @Override
    public String getLoaderID() {
        return "sound";
    }

    @Override
    public int getNumberOfLoadedObjects() {
        return numberOfLoadedObjects;
    }
}
