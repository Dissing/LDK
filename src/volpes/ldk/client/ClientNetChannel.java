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

import java.io.IOException;

/**
 * @author Lasse Dissing Hansen
 */
public interface ClientNetChannel {

    /**
     * Connects the client to a host
     * @param timeout The maximum time to wait for response
     * @param hostIP The ip address of the host
     * @param udpPort The port for UDP communication
     * @param tcpPort The port for TCP communication
     * @throws IOException
     */
    public void connect(int timeout, String hostIP, int udpPort, int tcpPort) throws IOException;

    /**
     * Connects the client to a host
     * Uses the the TCP port + 1 for UDP communication
     * @param timeout The maximum time to wait for response
     * @param hostIP The ip address of the host
     * @param tcpPort The port for TCP communication
     * @throws IOException
     */
    public void connect(int timeout, String hostIP, int tcpPort) throws IOException;

    /**
     * Connects the client to a host
     * Uses {@link Settings} to find defaults ports
     * @param timeout The maximum time to wait for response
     * @param hostIP The ip address of the host
     * @throws IOException
     */
    public void connect(int timeout, String hostIP) throws IOException;

    /**
     * Connects the client to a host
     * Uses {@link Settings} to find defaults port and 5 seconds for timeout
     * @param hostIP The ip address of the host
     * @throws IOException
     */
    public void connect(String hostIP) throws IOException;

    /**
     * Reconnects to the host with the latest parameters for connect
     * @throws IOException If unable to connect again
     */
    public void reconnect() throws IOException;

    /**
     * Closes the socket
     * @throws IOException
     */
    public void close() throws IOException;

    /**
     * Checks if the socket is currently connected to a host
     * @return True if connected
     */
    public boolean isConnected();

    /**
     * Return the oldest message in the inbox and removes it
     * @return The oldest message
     */
    public Object getMessage();

    /**
     * Checks if there currently is any messages in the inbox.
     * Always use before getting messages
     * @return True if message is available
     */
    public boolean messageAvailable();

    /**
     * Sends a message to the host over TCP
     * @param message The Objects to transmit
     */
    public void sendMessage(Object message);

}