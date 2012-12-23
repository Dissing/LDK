/*
 * Copyright (C) 2012 Lasse Dissing Hansen
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

package volpes.ldk;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

/**
 * @auther Lasse Dissing Hansen
 * @since 0.2
 */
public class Settings {

    private Map<String,Object> data;

    private boolean updated;

    public Settings(String file) {
        InputStream is = null;
        try {
            is = new FileInputStream(new File("engine.yml"));
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        Yaml yaml = new Yaml();
        data = (Map<String,Object>) yaml.load(is);
    }

    public boolean has(String key) {
        return data.containsKey(key);
    }

    public boolean getBool(String key) {
        return (boolean)data.get(key);
    }

    public int getInt(String key) {
        return (int)data.get(key);
    }

    public float getFloat(String key) {
        return (float)data.get(key);
    }

    public String getString(String key) {
        return (String)data.get(key);
    }

    public void setBool(String key, boolean value) {
        updated = true;
        data.put(key,value);
    }

    public void setInt(String key, int value) {
        updated = true;
        data.put(key,value);
    }

    public void setFloat(String key, float value) {
        updated = true;
        data.put(key,value);
    }

    public void setString(String key, String value) {
        updated = true;
        data.put(key,value);
    }

    protected boolean isUpdated() {
        return updated;
    }
}
