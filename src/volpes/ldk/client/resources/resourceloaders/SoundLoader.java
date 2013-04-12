package volpes.ldk.client.resources.resourceloaders;

import org.lwjgl.openal.AL10;
import org.lwjgl.util.WaveData;
import org.w3c.dom.Element;
import volpes.ldk.client.audio.ALMusic;
import volpes.ldk.client.audio.Music;
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

    private Map<String,Music>  musicMap = new HashMap<String,Music>();
    private int numberOfLoadedObjects = 0;

    @Override
    public void initialize() {

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
        String path = xmlElement.getTextContent();
        if (path == null || path.length() == 0)
            System.err.println("Image resource [" + id + "] has invalid path");

        Music music;
        try {
            InputStream is = VFS.getFile(path);
            WaveData waveFile =  WaveData.create(is);
            int buffer = AL10.alGenBuffers();
            if (AL10.alGetError() != AL10.AL_NO_ERROR)
                throw new LDKException("OpenAL is probably out of memory. Otherwise I wish you good luck!");
            AL10.alBufferData(buffer,waveFile.format,waveFile.data,waveFile.samplerate);
            waveFile.dispose();
            music = new ALMusic(buffer);

        } catch (FileNotFoundException e) {
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