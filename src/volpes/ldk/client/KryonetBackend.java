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

package volpes.ldk.client;

import com.esotericsoftware.kryonet.Client;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Lasse Dissing Hansen
 */
public class KryonetBackend implements ClientNetChannel {

    Client client;

    LinkedBlockingQueue<Object> inbox = new LinkedBlockingQueue<Object>();

   public KryonetBackend() {
       client = new Client();
       client.start();
   }

    @Override
    public void connect(int timeout, String hostIP, int udpPort, int tcpPort) throws IOException {
        client.connect(timeout,hostIP,udpPort,tcpPort);
    }

    @Override
    public void connect(int timeout, String hostIP, int tcpPort) throws IOException{
       connect(timeout,hostIP,tcpPort+1,tcpPort);
    }

    @Override
    public void connect(int timeout, String hostIP) throws IOException{
        connect(timeout, hostIP, Settings.has("udpport") ? Settings.getInt("udpport") : 37721, Settings.has("tcpport") ? Settings.getInt("tcpport") : 37720);
    }

    @Override
    public void connect(String hostIP) throws IOException{
        connect(5000,hostIP,Settings.has("udpport") ? Settings.getInt("udpport") : 37721,Settings.has("tcpport") ? Settings.getInt("tcpport") : 37720);
    }

    @Override
    public void reconnect() throws IOException{
        client.reconnect();
    }

    @Override
    public void close() throws IOException{
        client.close();
    }

    @Override
    public boolean isConnected() {
        return client.isConnected();
    }

    @Override
    public Object getMessage() {
        return inbox.poll();
    }

    @Override
    public boolean messageAvailable() {
        return !inbox.isEmpty();
    }

    @Override
    public void sendMessage(Object message) {
        client.sendTCP(message);
    }
}
