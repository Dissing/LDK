package volpes.ldk.client.process;

import java.util.ArrayList;

/**
 * @author Lasse Dissing Hansen
 */
public class ProcessManager {

    private ArrayList<Process> processList = new ArrayList<Process>();


    void attach(Process process) {
        processList.add(process);
    }

    void detach(Process process) {
        processList.remove(process);
    }

    void tickProcesses() {
        for (Process process : processList) {
            if (process.getState() != Process.PAUSED) {
                if (process.getState() != Process.DYING) {
                    process.tick();
                } else {
                    if (process.hasChild()){
                        attach(process.getChild());
                    }
                    detach(process);
                }
            }
        }

    }

    Process getProcess(int processId) {
        for (Process process : processList) {
            return process;
        }
        return null;
    }

}
