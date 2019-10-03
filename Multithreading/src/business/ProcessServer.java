package business;

import java.util.*;
import javax.swing.table.DefaultTableModel;
import userinterface.RequestStatistics;

/**
 * 
 * @author Kamini Prakash
 * 
 */
public class ProcessServer {
    private String name;
    private Queue waitingQueue;
    private RequestStatistics reqStat;
    private ArrayList<Request> processedRequests;

    public ProcessServer(String name, int queueSize) {
        this.name = name;
        this.waitingQueue = new Queue(queueSize);
        reqStat = new RequestStatistics();
        reqStat.nameLbl.setText(name);
        this.processedRequests = new ArrayList<>();
    }

    public ArrayList<Request> getProcessedRequests() {
        return processedRequests;
    }

    public RequestStatistics getReqStat() {
        return reqStat;
    }

    public String getName() {
        return name;
    }

    public Queue getWaitingQueue() {
        return waitingQueue;
    }

    public void populateServerTable() {

        try {

            DefaultTableModel defaultTableModel = (DefaultTableModel) reqStat.processedReqsTbl.getModel();
            defaultTableModel.setRowCount(0);
            Object rows[];
            Request serverRequest = null;
            if (!waitingQueue.isEmpty()) {

                for (int i = 0; i < waitingQueue.getSize(); i++) {
                    serverRequest = (Request) waitingQueue.getQueue()[i];
                    if (serverRequest != null && serverRequest.getStatus().equals("PENDING")) {
                        rows = new Object[2];
                        rows[0] = serverRequest.getReqName();
                        rows[1] = serverRequest.getStatus();

                        defaultTableModel.addRow(rows);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
