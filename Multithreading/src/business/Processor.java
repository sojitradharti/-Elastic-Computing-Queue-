package business;

import java.util.concurrent.CopyOnWriteArrayList;
import userinterface.mainJFrame;

/**
 * 
 * @author Kamini Prakash
 * 
 */

public class Processor implements Runnable {

    private CopyOnWriteArrayList<ProcessServer> servers;
    private mainJFrame mainFrame;
   
    public Processor(mainJFrame mainFrame, CopyOnWriteArrayList<ProcessServer> availServers) {
        this.mainFrame = mainFrame;
        this.servers = availServers;
    }

    @Override
    public void run() {
        while (servers != null) {

            for (ProcessServer server : servers) {
                if (!server.getWaitingQueue().isEmpty()) {
                    Request request = server.getWaitingQueue().dequeue();
                    try {
                        Thread.sleep((long) request.getProcessingTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                    request.setStatus("Done");
                    request.setEndTime(((System.currentTimeMillis())));
                    request.setResponseTime((int) (request.getEndTime() - request.getStartTime()));
                    server.getProcessedRequests().add(request);
                    mainFrame.populateCompletedReqTable(server, request);
                    server.populateServerTable();
                    System.out.println(request.getReqName() + " " + request.getStatus());
                }

            }

        }
    }

}
