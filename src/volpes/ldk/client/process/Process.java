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

package volpes.ldk.client.process;

/**
 * @author Lasse Dissing Hansen
 */
public interface Process {

    /**Just been made. Have not yet executed any code**/
    public static final int STARTED = 0;

    /**Executing code**/
    public static final int ACTIVE = 1;

    /**Currently paused**/
    public static final int PAUSED = 2;

    /**Dying on next update. Will not run any more code**/
    public static final int DYING = 3;


    public int getState();

    public void pause();

    public void unpause();

    public void kill();

    public boolean hasChild();

    public Process getChild();

    public boolean setChild(Process child);

    public void tick();

}
