package business;

import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;
import userinterface.mainJFrame;

/**
 *
 * @author Kamini Prakash
 */
public class Dispatcher implements Runnable {
    private RequestQueue requestQueue;
    private CopyOnWriteArrayList<ProcessServer> servers;
    private ServerList listofServer;
    private mainJFrame mainFrame;

    public Dispatcher(mainJFrame mainFrame, RequestQueue requestQueue, ServerList ListofServer, CopyOnWriteArrayList<ProcessServer> servers) {
        this.mainFrame = mainFrame;
        this.requestQueue = requestQueue;
        this.listofServer = ListofServer;
        this.servers = servers;
        ProcessServer processServer = ListofServer.getServers().get(0);
        servers.add(processServer);
        ListofServer.getServers().remove(processServer);
    }

    @Override
    public void run() {
        try {

            Queue<Request> q = requestQueue.getReqQueue();
            while (true) {
                synchronized (q) {
                    if (q.peek() != null) {
                        Thread.sleep(1000);
                        Request req = q.peek();
                        CopyOnWriteArrayList<ProcessServer> serverList = servers;
                        for (int i = serverList.size() - 1; i >= 0; i--) {
                            System.out.println("Available server size is " + serverList.size());
                            if (!req.getStatus().equals("PENDING")) {
                                if (!serverList.get(i).getWaitingQueue().isFull()) {
                                    mainFrame.getServerTabs().addTab(serverList.get(i).getName(), serverList.get(i).getReqStat());
                                    req.setStatus("PENDING");
                                    serverList.get(i).getWaitingQueue().enqueue(req);
                                    q.poll();
                                    mainFrame.populateRequestTable(requestQueue);
                                    mainFrame.populateServerPoolTable();
                                    serverList.get(i).populateServerTable();
                                } else if (i == 0 && (!listofServer.getServers().isEmpty())) {
                                    ProcessServer server = listofServer.getServers().get(0);
                                    servers.add(server);
                                    server.getWaitingQueue().enqueue(req);
                                    req.setStatus("PENDING");
                                    q.poll();
                                    mainFrame.populateRequestTable(requestQueue);
                                    mainFrame.getServerTabs().addTab(server.getName(), server.getReqStat());
                                    listofServer.getServers().remove(server);
                                    mainFrame.populateServerPoolTable();
                                    server.populateServerTable();
                                    break;
                                }
                            }
                        }

                    } else if (servers.size() > 1) {
                        for (ProcessServer pserver : servers) {
                            if (pserver.getWaitingQueue().isEmpty()) {
                                listofServer.getServers().add(pserver);
                                servers.remove(pserver);
                                mainFrame.populateServerPoolTable();
                                pserver.populateServerTable();
                                mainFrame.getServerTabs().remove(pserver.getReqStat());
                                System.out.println("Server Removed " + pserver.getName());
                            }
                        }
                    }

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public void startProcess() throws InterruptedException {
        Thread processorThread = new Thread(new Processor(mainFrame, servers));
        processorThread.start();
    }
}
