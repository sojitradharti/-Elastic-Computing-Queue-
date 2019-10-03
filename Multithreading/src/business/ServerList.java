package business;

import java.util.ArrayList;

/**
 * 
 * @author Sojit
 * 
 */
public class ServerList {
    private int threadPoolCount;
    private ArrayList<ProcessServer> servers;
    private int waitingQueueSize;

    public ServerList(int poolSize, int waitingqueueSize) {
        this.threadPoolCount = poolSize;
        this.waitingQueueSize = waitingqueueSize;
        servers = new ArrayList<>(poolSize);
        addServers();
    }

    public ArrayList<ProcessServer> getServers() {
        return servers;
    }

    private void addServers() {
        for (int i = 1; i <= threadPoolCount; i++) {
            ProcessServer processServer = new ProcessServer("Server " + i, waitingQueueSize);
            servers.add(processServer);
        }
    }

}
